#!/usr/local/bin/perl
#
# $Source: /projects/rdu/cvsroot/projects/zebulun/ASN1tojava/ASN1parser.pm,v $
# $Date: 1999/03/15 07:34:40 $
# $Revision: 1.2 $
#
# Copyright (C) 1996, Hoylen Sue.  All rights reserved.
#
# Abstract Syntax Notation 1 (ASN.1) parsing module.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
# the supplied license for more details.
#----------------------------------------------------------------

require 5; # requires Perl version 5 or higher

use ASN1element;

package ASN1parser;

#================================================================
# Input and token scanning routines

# This section uses the following global variables:
#
# lex_src_name     name of source for input
# lex_line         stores unprocessed part of current line
#                  If undef, means end of input has been reached.
# lex_trace        set for debugging the scanner

$lex_src = STDIN;
$lex_src_name = "<STDIN>";
$lex_trace = 0; # set to non-zero to debug trace the tokeniser

#----------------------------------------------------------------

sub token_init {
  # void token_init(file_handle source, string source_name);
  # Initialise the input scanner.

  ($lex_src, $lex_src_name) = @_;
  $lex_line = ""; # need to define it here
}

#----------------------------------------------------------------

sub token_get {
  # token token_get(void)
  # Returns the next token from the source which was set up by
  # token_init. If the end of the source has been reached and
  # there are no more tokens, an undefined value is returned.
  #
  # The tokenizer is designed for ASN.1 input. Special ASN.1 two word
  # tokens are returned as one token (e.g. "OBJECT IDENTIFIER", "BIT STRING").

  while (1) {
    if (! defined($lex_line)) {

      if ($lex_trace) {
	print "TOKEN()\n"; # scan-debug
      }

      return undef; # failed, end of input
    }
      
    if (! ($lex_line =~ /^\s*$/)) {
      # Line is not blank, so it will contain the next token

      $lex_line =~ s/^\s*//; # remove leading whitespace

      # Scan contents of the line for the next token

      if ($lex_line =~ s/^([A-Za-z0-9][A-Za-z0-9-]*)//) {
	# matched a word token
	
	my($token) = $1;

	if ($lex_trace) {
	  print "TOKEN($token)\n"; # scan-debug
	}

	# Special checking for multi-word tokens of ASN.1

	if ($token eq "OBJECT") {
	  my($part2) = token_get(); # recursive call
	  if (! defined($part2)) {
	    die token_error("`OBJECT' token incomplete\n");
	  }
	  $token = "OBJECT $part2";

	} elsif ($token eq "BIT") {
	  my($part2) = token_get(); # recursive call

	  if ($part2 ne "STRING") {
	    die token_error("`BIT STRING' token expected\n");
	  }
	  $token = "$token $part2";

	} elsif ($token eq "OCTET") {
	  my($part2) = token_get(); # recursive call

	  if ($part2 ne "STRING") {
	    die token_error("`OCTET STRING' token expected\n");
	  }
	  $token = "$token $part2";
	}

	# Return the token matched

	return $token; # success

      } elsif (($lex_line =~ s/^(\:\:\=)//) ||
	       ($lex_line =~ s/^([^A-Za-z0-9])//)) {
	# matched a non-word token `::=' or single character punctuation

	my($token) = $1;

	if ($lex_trace) {
	  print "TOKEN($token)\n"; # scan-debug
	}

	return $token; # success

      } else {
	# did not match any of the above token rules

	die token_error("internal error in token_get routine\n");
      }

    } else {
      # Line is empty, so try to read more input from the source
      
      $lex_line = <$lex_src>; # read next line from the source

      if (defined($lex_line)) {
	$lex_line =~ s/--.*$//; # remove comments
      }
    }
  } # while (1)
}

#----------------------------------------------------------------

sub token_unget {
  # void token_unget(token tk)
  # Puts back a token for getting on the next call to token_get.

  my($tk) = @_;

  if ($lex_trace) {
    print "TOKEN_UNGET($tk)\n";
  }

  $lex_line = $tk . " " . $lex_line;
}

#----------------------------------------------------------------

sub token_error {
  # string token_error(string message)
  # Generates a labled error message.

  my($msg) = @_;
  return "$lex_src_name: $.: " . $msg;
}

#================================================================
# ASN.1 parsing routines

# This section uses the following global variables:
# $parse_trace   toggle for debug trace information, doubles as level

$parse_trace = 0; # set to 1 to trace the parser

#----------------------------------------------------------------

sub parse_element {
  my($mod_default_tags) = @_;

  # element??? parse_element(void)

  if ($parse_trace) {
    # Increment level for pretty printing
    $parse_trace++;
  }

  # Create new ASN1element object to store this element

  my $element = ASN1element->new();

  # Get the next token

  $token = token_get();
  die token_error("unexpected end of file") if (! defined($token));

  # Check for optional name (which always start with a lowercase letter)

  if ($token =~ /^[a-z]/) {
    # Element has a name

    if (! ($token =~ /^[a-z]/)) {
      die token_error("identifier does not start " .
		      "with a lower case letter: `$token'");
    }
    if ($token =~ /\-$/) {
      die token_error("identifier cannot end with a hyphen: `$token'");
    }
    if ($token =~ /\-\-/) {
      die token_error("identifier cannot contain double hyphen: `$token'");
    }

    $element->name_set($token);

    $token = token_get();
    die token_error("unexpected end of file") if (! defined($token));
  }

  # Check for optional tag

  my($tag_number) = -1;
  my($tag_class) = "CONTEXT_SPECIFIC"; # default
  my($tag_explicit) = 0;

  if ($token eq "[") {
    # There is a tag for this element

    # Extract tag number

    $token = token_get();
    die token_error("unexpected end of file") if (! defined($token));

    
    if ($token eq "UNIVERSAL" ||
	$token eq "APPLICATION" ||
	$token eq "PRIVATE") {
      $tag_class = $token;

      $token = token_get();
      die token_error("unexpected end of file") if (! defined($token));
    }

    if (! ($token =~ /^[0-9]+$/)) {
      die token_error("badly formatted tag number");
    }
    $tag_number = $token;
    if ($tag_number < 0) {
      die token_error("invalid negative tag number");
    }
    
    # Skip right square bracket

    $token = token_get();
    die token_error("unexpected end of file") if (! defined($token));

    if ($token ne "]") {
      die token_error("unexpecting `$token', expecting `]'\n");
    }

    # Get next token and check for optional IMPLICIT/EXPLICIT tagging

    $token = token_get();
    die token_error("unexpected end of file") if (! defined($token));

    if ($token eq "IMPLICIT") {
      $tag_explicit = 0;

    } elsif ($token eq "EXPLICIT") {
      $tag_explicit = 1;

    } else {
      if ($mod_default_tags eq "EXPLICIT") {
	$tag_explicit = 1;
      } else {
	$tag_explicit = 0;
      }
      token_unget($token);
    }

    $element->tag_set($tag_class, $tag_number, $tag_explicit);

    # end of [tag] processing
  } else {
    token_unget($token);
  }

  # Parse the type

  my($type, $type_value, $type_value1) = parse_type($mod_default_tags);

  $element->type_set($type, $type_value, $type_value1);

  # Check for OPTIONAL

  my($is_optional) = 0;

  $token = token_get();

  if (defined($token)) {
    if ($token eq "OPTIONAL") {
      $element->optional_set(1);
      
    } else {
      token_unget($token); # the token is for the next production
    }
  } # else { end of file, so no OPTIONAL }

  # Create object to store element information

  if ($parse_trace) {
    # Print debug trace information

    my($indenting) = "";
    for ($x = 1; $x < $parse_trace; $x++) { $indenting .= "  "; } # indenting

    print $element->toString($indenting);

    $parse_trace--;
    if ($parse_trace <= 0) {
      die "Internal error: mismatch in \$parse_trace decrementing\n";
    }
  }

  return $element;
}

#----------------------------------------------------------------

sub parse_type {
  my($mod_default_tags) = @_;

  # Extract type

  my($type, $type_value, $type_value1);
  
  my($token) = token_get();
  die token_error("unexpected end of file") if (! defined($token));

  if ($token eq "SEQUENCE") {
    # A SEQUENCE element

    $token = token_get();
    die token_error("unexpected end of file") if (! defined($token));

    if ($token eq "OF") {
      # A SEQUENCE OF element

      $type = "SEQUENCE OF";

      my($t) = token_get();
      if ($t eq "SEQUENCE" || $t eq "CHOICE") {
	# SEQUENCE OF SEQUENCE
	# SEQUENCE OF CHOICE

	token_unget($t);
	my $dummy;
	($type_value, $type_value1, $dummy) = parse_type($mod_default_tags);
	die "Internal error in ASN1 parser\n" if (defined($dummy));

      } else {
	# SEQUENCE OF type

	token_unget($t);
	my $dummy;
	my $dummy1;
	($type_value, $dummy, $dummy1) = parse_type($mod_default_tags);

	die "Internal error in ASN1 parser\n" if (defined($dummy1));
      }

    } elsif ($token eq "{") {
      # Inline sequence

      token_unget($token); # push back `{' token for call

      $type = "SEQUENCE";
      $type_value = parse_sequence($mod_default_tags);

    } else {
      die token_error("internal error in parse_element for SEQUENCE\n");
    }

  } elsif ($token eq "SET") {
    # A SET OF

    $token = token_get();
    die token_error("unexpected end of file") if (! defined($token));

    if ($token ne "OF") {
      die token_error("internal error in parse_element for SET OF\n");
    }
      
    $type = "SET OF";
    
    my($t) = token_get();
    if ($t eq "SEQUENCE" || $t eq "CHOICE") {
      # SET OF SEQUENCE
      # SET OF CHOICE

      token_unget($t);
      my $dummy;
      ($type_value, $type_value1, $dummy) = parse_type($mod_default_tags);
      die "Internal error in ASN1 parser\n" if (defined($dummy));

    } else {
      # SET OF type

      token_unget($t);
      my $dummy;
      my $dummy1;
      ($type_value, $dummy, $dummy1) = parse_type($mod_default_tags);

      die "Internal error in ASN1 parser\n" if (defined($dummy));
      die "Internal error in ASN1 parser\n" if (defined($dummy1));
    }

  } elsif ($token eq "CHOICE") {
    # A CHOICE

    $type = "CHOICE";
    $type_value = parse_choice($mod_default_tags);

  } elsif ($token eq "BIT STRING") {
    # Handle BIT STRINGS, extracting possible bits names

    $type = "BIT STRING";

    $token = token_get();
    if ($token eq "{") {
      # Extract bit names

      # Discard contents for now ???
      do {
	$token = token_get();
	die token_error("unexpected end of file") if (! defined($token));
      } while ($token ne "}");

    } else {
      # No bit names
      token_unget($token); # push back token
    }

  } elsif ($token eq "INTEGER") {
    # Handle INTEGER

    my($tk) = token_get();

    if (defined($tk) && ($tk eq "{")) {
      # Enumerated integer values

      my %enum_values;

      do {
	my $name = token_get();
	die token_error("unexpected end of file") if (! defined($name));

	$tk = token_get();
	die token_error("unexpected end of file") if (! defined($tk));
	if ($tk ne "(") {
	  die token_error("unexpecting `$tk', expecting `('");
	}

	my $value = token_get();
	die token_error("unexpected end of file") if (! defined($value));

	$tk = token_get();
	die token_error("unexpected end of file") if (! defined($tk));
	if ($tk ne ")") {
	  die token_error("unexpecting `$tk', expecting `)'");
	}

	$enum_values{$name} = $value;

	$tk = token_get();
	die token_error("unexpected end of file") if (! defined($tk));
	
      } while ($tk eq ",");

      if ($tk ne "}") {
	die token_error("unexpecting `$tk', expecting `}'");
      }

      $type_value = \%enum_values;

    } elsif (defined($tk) && ($tk eq "(")) {
      # A subtype

      # ignore for now???

      $tk = token_get(); # min
      die token_error("unexpected end of file") if (! defined($tk));

      $tk = token_get(); # .
      die token_error("unexpected end of file") if (! defined($tk));
      $tk = token_get(); # .
      die token_error("unexpected end of file") if (! defined($tk));

      $tk = token_get(); # max
      die token_error("unexpected end of file") if (! defined($tk));

      $tk = token_get(); # closing "("
      die token_error("unexpected end of file") if (! defined($tk));

    } else {
      # Straight INTEGER, token is not a part of type
      token_unget($tk);
    }

    $type = "INTEGER";

  } elsif ($token eq "ENUMERATED") {
    # Handle ENUMERATED

    my($tk) = token_get();
    die token_error("unexpected end of file") if (! defined($tk));

    if ($tk ne "{") {
      die token_error("unexpecting `$tk', expecting `{'\n");
    }
    
    my %enum_values;

    do {
      my $name = token_get();
      die token_error("unexpected end of file") if (! defined($name));

      $tk = token_get();
      die token_error("unexpected end of file") if (! defined($tk));
      if ($tk ne "(") {
	die token_error("unexpecting `$tk', expecting `('");
      }

      my $value = token_get();
      die token_error("unexpected end of file") if (! defined($value));

      $tk = token_get();
      die token_error("unexpected end of file") if (! defined($tk));
      if ($tk ne ")") {
	die token_error("unexpecting `$tk', expecting `)'");
      }

      $enum_values{$name} = $value;

      $tk = token_get();
      die token_error("unexpected end of file") if (! defined($tk));
      
    } while ($tk eq ",");

    if ($tk ne "}") {
      die token_error("unexpecting `$tk', expecting `}'");
    }

    $type_value = \%enum_values;

    $type = "ENUMERATED";

  } else {
    # Type by name

    if (! ($token =~ /^[A-Z]/)) {
      die token_error("type reference does not start " .
		      "with a capital letter: `$token'");
    }
    if ($token =~ /\-$/) {
      die token_error("type reference cannot end with a hyphen: `$token'");
    }
    if ($token =~ /\-\-/) {
      die token_error("type reference cannot contain double hyphen: " .
		      "`$token'");
    }

    $type = $token;
  }

  return ($type, $type_value, $type_value1);
}

#----------------------------------------------------------------

sub parse_choice {
  my($mod_default_tags) = @_;

  if ($parse_trace) {
    for ($x = 1; $x < $parse_trace; $x++) { print "  "; } # indenting
    print "parsing CHOICE {\n";
  }

  my($choices) = parse_list($mod_default_tags);

  if ($parse_trace) {
    for ($x = 1; $x < $parse_trace; $x++) { print "  "; } # indenting
    print "} -- parsing CHOICE\n";
  }

  return $choices;
}

#----------------------------------------------------------------

sub parse_sequence {
  my($mod_default_tags) = @_;

  if ($parse_trace) {
    for ($x = 1; $x < $parse_trace; $x++) { print "  "; } # indenting
    print "parsing SEQUENCE {\n";
  }

  my($elements) = parse_list($mod_default_tags);

  if ($parse_trace) {
    for ($x = 1; $x < $parse_trace; $x++) { print "  "; } # indenting
    print "} -- parsing SEQUENCE\n";
  }

  return $elements;
}

#----------------------------------------------------------------

sub parse_list {
  my($mod_default_tags) = @_;

  # Extract open brace

  my($token) = token_get();
  die token_error("unexpected end of file") if (! defined($token));

  if ($token ne "{") {
    die token_error("`{' not found in CHOICE: `$token'");
  }

  # Extract body elements of list

  my(@list);

  do {
    push(@list, parse_element($mod_default_tags));

    $token = token_get();
    die token_error("unexpected end of file") if (! defined($token));
  } while ($token eq ",");

  # Extract close brace

  if ($token ne "}") {
    die token_error("unexpected `$token', expecting `}'\n");
  }

  return \@list;
}

#----------------------------------------------------------------

sub parse_asn1 {
  my($filename) = @_;

  my $mod_default_tags = "EXPLICIT"; # ASN.1's default tag form is explicit

  my($src, $src_name);
  if ($filename eq "-") {
    $src = STDIN;
    $src_name = "<STDIN>";
  } else {
    open(ASN1, $filename) || die "Could not open file: `$filename': $!\n";
    $src = ASN1;
    $src_name = $filename;
  }

  token_init($src, $src_name);

  my(@modules);

  for (;;) {
    # Extract definition name

    my($token) = token_get();
    if (! defined($token)) {
      last;
    }

    my($module_reference);
    if ($token ne "{") {
      # There is a module reference

      if (! ($token =~ /^[A-Z]/)) {
	die token_error("type reference does not start " .
			"with a capital letter: `$token'");
      }
      if ($token =~ /\-$/) {
	die token_error("type reference cannot end with a hyphen: `$token'");
      }
      if ($token =~ /\-\-/) {
	die token_error("type reference cannot contain double hyphen: " .
			"`$token'");
      }

      $module_reference = $token;

      $token = token_get();
      die token_error("unexpected end of file") if (! defined($token));
    } else {
      # There is no module reference, generate a name for it

      my($already_exists);
      my($n) = 0;
      do {
	$module_reference = "Untitled$n";
	
	$already_exists = 0;
	foreach (@modules) {
	  my(@mod) = @$_;
	  if ($mod[0] eq $module_reference) {
	    last;
	  }
	}
	$n++;
      } while ($already_exists);
    }

    if ($parse_trace) {
      for ($x = 1; $x < $parse_trace; $x++) { print "  "; } # indenting
      print "DEFINITION $module_reference ---\n";
    }

    # Check for optional OID

    my($mod_oid);

    if ($token eq "{") {
      # Compound definition name

      $mod_oid = "{";

      do {
	$token = token_get();
	die token_error("unexpected end of file") if (! defined($token));

	$mod_oid .= " $token";

      } while ($token ne "}");

      $token = token_get();
      die token_error("unexpected end of file") if (! defined($token));
    }

    if ($parse_trace && defined($mod_oid)) {
      for ($x = 1; $x < $parse_trace; $x++) { print "  "; } # indenting
      print "OID $mod_oid ---\n";
    }

    # Extract DEFINITIONS

    if ($token ne "DEFINITIONS") {
      die token_error("`DEFINITIONS' expected: `$token'");
    }

    # Extract optional IMPLICIT TAGS

    $token = token_get();
    die token_error("unexpected end of file") if (! defined($token));

    if ($token eq "IMPLICIT" || $token eq "EXPLICIT") {
      $mod_default_tags = $token;

      $token = token_get();
      die token_error("unexpected end of file") if (! defined($token));

      if ($token ne "TAGS") {
	die token_error("`TAGS' expected: `$token'");
      }

      $token = token_get();
      die token_error("unexpected end of file") if (! defined($token));
    }

    # Extract `::=' token

    if ($token ne "::=") {
      die token_error("`::=' expected: `$token'");
    }

    # Extract `BEGIN' token

    $token = token_get();
    die token_error("unexpected end of file") if (! defined($token));

    if ($token ne "BEGIN") {
      die token_error("`BEGIN' expected: `$token'");
    }

    # Get next token

    $token = token_get();
    die token_error("unexpected end of file") if (! defined($token));

    # Extract optional EXPORTS list

    if ($token eq "EXPORTS") {
      # There is an EXPORTS list, process it

      do {
	$token = token_get();
	die token_error("unexpected end of file") if (! defined($token));
      } while ($token ne ";");

      $token = token_get();
      die token_error("unexpected end of file") if (! defined($token));
    }

    # Extract optional IMPORTS list

    my %imports;

    while ($token eq "IMPORTS") {
      # There is an IMPORTS list, process it

      do {
	my @symbols;

	for (;;) {
	  $token = token_get();
	  die token_error("unexpected end of file") if (! defined($token));
	  if ($token eq "FROM") {
	    last;
	  } else {
	    print "Importing: $token\n" if ($parse_trace);

	    push(@symbols, $token);

	    $token = token_get();
	    die token_error("unexpected end of file") if (! defined($token));
	    token_unget($token) if ($token ne ",");
	  }
	}
	$token = token_get();
	die token_error("unexpected end of file") if (! defined($token));

	$imports{$token} = \@symbols;
	print "from $token\n" if ($parse_trace);

	$token = token_get();
	die token_error("unexpected end of file") if (! defined($token));

	token_unget($token) if ($token ne ";"); # more imports to go

      } while ($token ne ";");

      $token = token_get();
      die token_error("unexpected end of file") if (! defined($token));
    }

    # Push back $token for processing of productions

    token_unget($token);

    # Process all productions

    my(%definitions);

    for (;;) {
      # Extract next production name

      my($token) = token_get();
      die token_error("unexpected end of file") if (! defined($token));

      if ($token ne "END") {
	# A production

	if (! ($token =~ /^[A-Z]/)) {
	  die token_error("type reference does not start " .
			  "with a capital letter: `$token'");
	}
	if ($token =~ /\-$/) {
	  die token_error("type reference cannot end with a hyphen: `$token'");
	}
	if ($token =~ /\-\-/) {
	  die token_error("type reference cannot contain double hyphen: " .
			  "`$token'");
	}
	if (! ($token =~ /^[a-zA-Z][a-zA-Z0-9-]*/)) {
	  die token_error("badly formatted type reference: `$token'\n");
	}

	my($production) = $token;

	# Extract `::=' token

	$token = token_get();
	die token_error("unexpected end of file") if (! defined($token));

	if ($token ne "::=") {
	  die token_error("`::=' expected: `$token'");
	}

	# Extract the production definition

	if ($parse_trace) {
	  for ($x = 1; $x < $parse_trace; $x++) { print "  "; } # indenting
	  print "\n";
	  print "parsing definition of `$production'\n"; #???
	}

	# Process production

	$definitions{$production} = parse_element($mod_default_tags);

      } else {
	# END reached

	my(@module) = ($module_reference, $mod_oid, \%definitions, \%imports);
	push(@modules, \@module);

	last; # end of definitions loop
      }

    } # for (over productions of a definition)

    if ($parse_trace) {
      for ($x = 1; $x < $parse_trace; $x++) { print "  "; } # indenting
      print "END $module_reference ---\n";
    }

  } # for (over modules in a file)

  if ($filename ne "-") {
    close(ASN1);
  }

  return \@modules;
}

#================================================================
# Flatten the ASN.1 representation so that it can be implemented
# using one level deep data structures.

sub asn1_flatten {
  # Flattens the definitions of a given module.
  my($module_defs, $verbose) = @_;

  my $generated_production_prefix = ""; # Prefix of new production names

  foreach (keys(%$module_defs)) {
    # Process each production
    my($production_name) = $_;

    asn1_flatten_def($verbose, $module_defs,
		     $module_defs->{$production_name},
		     $generated_production_prefix . $production_name);
  }
}

sub asn1_flatten_newname {
  # Devise a new unique name for the production

  my($defs, $newtype) = @_;

  if (defined($defs->{$newtype})) {
    # Name already defined, generate one that is not by appending a
    # new number until a name which is not used is found.

    my($count) = 1;
    my($base) = $newtype;

    do {
      $newtype = $base . $count;
      $count++;
    } while(defined($defs->{$newtype}));
  }

  return $newtype;
}

sub asn1_flatten_def {
  # Flatten a single definition, referenced by $def_ref, this
  # comes from the module referenced by $defs, and the given
  # $prefix name should be used to prefix any new definitions created.

  my($verbose, $defs, $def_ref, $prefix) = @_;
  my($type, $type_value, $type_value1) = $def_ref->type_get();

  if ($type eq "CHOICE" ||
      $type eq "SEQUENCE") {
    # The production is a choice or sequence, this means it cannot
    # itself contain any sequences or choices as elements

    if (defined($type_value1)) {
      die("Internal Error: " .
	  "asn1_flatten_def: SEQUENCE/CHOICE: extra data\n");
    }

    # Flatten out each element which is constructed

    foreach (@$type_value) {
      my $element = $_;
      my $ename = $element->name_get();
      my($etype, $etype_value, $etype_value1) = $element->type_get();
      my $new_prefix = $prefix;

      if (defined($ename)) {
	$new_prefix = $prefix . "_" . $ename;
      }

      if ($etype eq "SEQUENCE" ||
	  $etype eq "CHOICE") {
	# Can't have this, must split this out

	my $newtype = asn1_flatten_newname($defs, $new_prefix);

	# Define the new production

	print "Creating new production: $newtype\n" if ($verbose);

	my $newelement = ASN1element->new();
	$newelement->type_set($etype, $etype_value, undef);
	$defs->{$newtype} = $newelement;

	# Set the old type to be the new type

	$element->type_set($newtype, undef, undef);

        # Recursively flatten the new production

        asn1_flatten_def($verbose, 
			 $defs, $newelement, $new_prefix); # recursive

      } else {
	# Recursively flatten

	asn1_flatten_def($verbose, $defs, $_, $prefix); # use old prefix!
      }
    }

  } elsif ($type eq "SEQUENCE OF" && ($type_value eq "SEQUENCE" ||
				      $type_value eq "CHOICE")) {
    # These should have an array of elements in their $type_value1

    if (! defined($type_value1)) {
      die("Internal Error: " .
	  "asn1_flatten_def: SEQUENCE OF SEQUENCE missing data\n");
    }

    # Devise a new unique name for the production

    my $new_prefix = $prefix;

    my $name = $def_ref->name_get();
    if (defined($name)) {
      $new_prefix = $prefix . "_" . $name;
    }

    my $newtype = asn1_flatten_newname($defs, $new_prefix);

    # Set the old type to be a sequence of the new type

    $def_ref->type_set("SEQUENCE OF", $newtype, undef);

    # Define the new production

    print "Creating new production: $newtype\n" if ($verbose);

    my $newelement = ASN1element->new();
    $newelement->type_set($type_value, $type_value1, undef);
    $defs->{$newtype} = $newelement;

    # Recursively flatten the new production

    asn1_flatten_def($verbose, $defs, $newelement, $prefix); # recursive

  } else {
    # Any other type does not need to be flatten
  }
}

#----------------------------------------------------------------

1; # return value for package

#----------------------------------------------------------------
# $Log: ASN1parser.pm,v $
# Revision 1.2  1999/03/15 07:34:40  hoylen
# Implemented experimental XER encoding and decoding
#
# Revision 1.1.1.1  1998/12/29 00:19:43  hoylen
# Imported sources
#
# Revision 1.8  1998/09/08 03:11:14  hoylen
# Fixed style bug in code
#
# Revision 1.7  1998/04/23 12:57:46  hoylen
# Removed period as a part of token
#
# Revision 1.6  1998/04/22 13:38:25  hoylen
# Added period to be permitted as a part of tokens
#
# Revision 1.5  1997/11/12 12:29:07  hoylen
# Fixed bug with using $mod_default_tags incorrectly
#
# Revision 1.4  1997/10/07 06:54:03  hoylen
# Changed RCS comment format
#
# Revision 1.3  1997/10/05 09:17:44  hoylen
# Fixed bug with default tag form
#
# Revision 1.2  1997/10/04 07:51:46  hoylen
# Added support of enumerations and SET OF
#
# Revision 1.1  1997/04/09 11:08:48  hoylen
# Initial revision
#
#----------------------------------------------------------------
#EOF
