#!/usr/bin/perl
# ONLY TO BE USED WHEN MAVEN CANNOT BE INSTALLED
use strict;
# To be updated
system "wget https://github.com/ulyfm/FD2/releases/download/0.2b/FD2.jar"
system "mv FD2.jar FoodDrop.jar"

# Need to be updated slightly less frequently
system "mkdir FD_lib"
system "wget https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/json-simple/json-simple-1.1.1.jar"
system "mv json-simple-1.1.1.jar FD_lib/json-simple-1.1.1.jar"
system "wget https://bitbucket.org/xerial/sqlite-jdbc/downloads/sqlite-jdbc-3.14.2.1.jar"
system "mv sqlite-jdbc-3.14.2.1.jar FD_lib/sqlite-jdbc-3.14.2.1.jar"