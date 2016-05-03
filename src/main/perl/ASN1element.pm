#!/usr/local/bin/perl
#
# $Source: /projects/rdu/cvsroot/projects/zebulun/ASN1tojava/ASN1element.pm,v $
# $Date: 1998/12/29 00:19:43 $
# $Revision: 1.1.1.1 $
#
# Copyright (C) 1996, Hoylen Sue.  All rights reserved.
#
# This module defines a class for representing an ASN.1 element.
# This class is used to represent the productions parsed by
# the ASN.1 parser and is manipulated by other modules.
# An element is a single ASN.1 specification
# e.g. fooType [42] IMPLICIT OBJECT IDENTIFIER OPTIONAL
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
# the supplied license for more details.
#----------------------------------------------------------------

package ASN1element;

# The contents of this object are stored in an associated array
# containing the following elements:
#
# name (optional)
# tag_number
# tag_explicit		Boolean value
# type
# type_value
# type_value1
# is_optional           Boolean value
#
# Cases:
#
# SEQUENCE OF SEQUENCE { foo }
#          type = "SEQUENCE OF"
#    type_value = "SEQUENCE"
#   type_value1 = { foo }
#
# SEQUENCE { foo }
#          type = "SEQUENCE"
#    type_value = { foo }
#   type_value1 = undef
#
# SET OF { foo }
#          type = "SET OF"
#    type_value = { foo }
#   type_value1 = undef
#
# CHOICE { foo }
#          type = "CHOICE"
#    type_value = { foo }
#   type_value1 = undef
#
# bar
#          type = "bar"
#    type_value = undef
#   type_value1 = undef
#

#----------------------------------------------------------------
# constructor
#
# Makes a production object, which is mandatory by default.

sub new {
  my($class) = @_;
  
  my($self) = {"is_optional" => 0}; # false, i.e. mandatory

  bless $self;
  return $self;
}

#================================================================
# name_set
#
# Sets the name of the ASN.1 element

sub name_set {
  my $self = shift;
  my($name) = @_;

  $self->{"name"} = $name;
}

#----------------------------------------------------------------
# name_set
#
# Returns the name of the ASN.1 element. This only returns a
# defined value if name_set was previously called on the object.

sub name_get {
  my($self) = shift;

  return $self->{"name"};
}

#================================================================
# tag_set($tag_class, $tag_number, $is_explicit)
#
# Sets the tag of the ASN.1 element to the given $number, and
# whether it $is_explicit.

sub tag_set {
  # number, is_explicit

  my($self) = shift;
  my($tag_class, $tag_number, $is_explicit) = @_;
  
  $self->{"tag_class"} = $tag_class;
  $self->{"tag_number"} = $tag_number;
  $self->{"tag_explicit"} = $is_explicit;
}

#----------------------------------------------------------------
# is_tagged()
#
# Returns true (non-zero) if the ASN.1 element is tagged, otherwise
# false (zero).

sub is_tagged {
  my($self) = shift;

  return (defined($self->{"tag_number"})) ? 1 : 0;
}

#----------------------------------------------------------------
# tag_get()
#
# Returns the tag number and a Boolean indicating if it is an
# explicit tag. The result is undefined if is_tagged returns false.

sub TAG_get {
  my $self = shift;

  return ($self->{"tag_class"},
	  $self->{"tag_number"},
	  $self->{"tag_explicit"});
}

#================================================================
# type_set($type, $type_value, $type_value1)
#
# Sets the type of the ASN.1 element.

sub type_set {
  my($self) = shift;
  my($type, $type_value, $type_value1) = @_;

  $self->{"type"} = $type;
  $self->{"type_value"} = $type_value;
  $self->{"type_value1"} = $type_value1;
}

#----------------------------------------------------------------
# ($type, $value, $value1) type_get()
#
# Returns the type and its type value.

sub type_get {
  my($self) = shift;

  return ($self->{"type"},
	  $self->{"type_value"},
	  $self->{"type_value1"});
}

#================================================================
# optional_set($opt)
#
# Sets whether the ASN.1 element is optional ($opt being true) or not
# ($opt is false).

sub optional_set {
  my($self) = shift;
  my($opt) = @_;

  $self->{"is_optional"} = $opt;
}

#----------------------------------------------------------------
# is_optional()
#
# Returns true if the ASN.1 element is optional

sub is_optional {
  my $self = shift;

  return $self->{"is_optional"};
}

#================================================================
# toString($indenting)
#
# Returns a text string representation of the ASN.1 element.
# The string is prefixed with $indenting on each line, if it is
# defined.

sub toString {
  my($self) = shift;
  my($indenting) = @_;

  my($result);

  if (! defined($indenting)) {
    $indenting = "";
  }

  $result = $indenting;

  # Name

  my($name) = $self->{"name"};
  if (defined($name)) {
    $result .= "$name ";
  }

  # Tagging

  if ($self->is_tagged()) {
    $result .= "[" . $self->{"tag_number"} . "] ";
    $result .= (($self->{"tag_explicit"}) ? "EXPLICIT " : "IMPLICIT ");
  }

  # Type

  my($type) = $self->{"type"};
  $result .= "$type";

  if ($type eq "CHOICE" ||
      $type eq "SEQUENCE") {
    # These have array of elements in their type_value

    my(@choices) = @{$self->{"type_value"}};

    $result .= " {\n";

    foreach (@choices) {
      my($choice) = $_;

      $result .= $_->toString($indenting . "  ");
    }

    $result .= $indenting . "}";

  } elsif ($type eq "SEQUENCE OF" ||
	   $type eq "SET OF") {
    my $type_value = $self->{"type_value"};

    die "ASN1Element: Internal error: missing value in SEQUENCE OF\n"
      if (! defined($type_value));
    
    if ($type_value eq "SEQUENCE") {
      # SEQUENCE OF SEQUENCE, array stored in type_value1

      my $type_value1 = $self->{"type_value1"};

      die "ASN1Element: Internal error: missing value in SEQUENCE OF\n"
	if (! defined($type_value1));

      my(@choices) = @{$type_value1};

      $result .= " {\n";

      foreach (@choices) {
	my($choice) = $_;

	$result .= $_->toString($indenting . "  ");
      }

      $result .= $indenting . "}";

    } else {
      # Ordinary SEQUENCE OF typename

      $result .= " " . $self->{"type_value"};
    }
  }

  # Optionality

  if ($self->{"is_optional"}) {
    $result .= " OPTIONAL";
  }

  # Finish off

  $result .= "\n";
  
  return $result;
}

#----------------------------------------------------------------

1; # return value for package

#----------------------------------------------------------------
#EOF
