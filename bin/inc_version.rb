#!/usr/bin/env ruby
require_relative 'lib/shell.rb' # requires Ruby 1.9
require_relative 'get_version.rb'

version, build =* get_version

new_build = (build.to_i + 1).to_s
new_version = version + new_build

puts "will set new version #{new_version}"
mvn_command = "mvn versions:set -DnewVersion=#{new_version}"
shell(mvn_command)
