#!/usr/bin/env ruby
require_relative 'lib/shell.rb'

Dir.chdir('application')

puts "will create docker image"
# Maven runs docker build -f application/src/main/docker/Dockerfile -t hans-and-peter.game.geography:1.0.0-2 application/
mvn_command = 'mvn docker:build'
shell(mvn_command)

# docker tag fails on Snap-CI. Use command line instead if we want a latest
# docker_command = 'docker tag hanscoder/hans-and-peter.game.geography:#{version} hanscoder/hans-and-peter.game.geography:latest'
