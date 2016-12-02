#!/usr/bin ruby
require_relative '../lib/environment'

raise 'Usage: bin/app.rb <pfad>' unless ARGV.length == 1
raise 'Datei nicht gefunden!' unless File.file? ARGV[0]
puts Main.new(ARGV[0]).run
