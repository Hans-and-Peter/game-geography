#!/usr/bin/env ruby
require_relative 'lib/shell.rb'

Dir.chdir('application')

puts "will create docker image"
mvn_command = 'mvn docker:build'
shell(mvn_command)

# docker tag fails on Snap-CI. Use command line instead if we want a latest
# docker_command = 'docker tag hanscoder/hans-and-peter.game.geography:#{version} hanscoder/hans-and-peter.game.geography:latest'
