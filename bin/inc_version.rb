#!/usr/bin/env ruby
require_relative 'shell.rb'

# see http://stackoverflow.com/a/3545363
mvn_command = 'mvn org.apache.maven.plugins:maven-help-plugin:evaluate -Dexpression=project.version'
mvn_stdout = shell(mvn_command)

version_pattern = /^(\d+.*-)(\d)$/
if mvn_stdout !~ version_pattern
  puts mvn_stdout
  raise "version number #{version_pattern} not found"
end
puts "found version #{$1}#{$2}"

version = $1
build = $2.to_i
new_build = (build + 1).to_s
new_version = version + new_build

puts "will set new version #{new_version}"
mvn_command = "mvn versions:set -DnewVersion=#{new_version}"
shell(mvn_command)
