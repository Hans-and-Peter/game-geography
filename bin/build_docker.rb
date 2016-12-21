#!/usr/bin/env ruby
require_relative 'shell.rb'

Dir.chdir('application')

puts "will create docker image"
mvn_command = 'mvn docker:build'
shell(mvn_command)
