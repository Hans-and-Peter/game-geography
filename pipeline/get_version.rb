#!/usr/bin/env ruby
require_relative 'lib/shell.rb' # requires Ruby 1.9

# Get the project version from Maven POM. Return []version, build-number].
def get_version
  # see http://stackoverflow.com/a/3545363
  mvn_command = 'mvn org.apache.maven.plugins:maven-help-plugin:evaluate -Dexpression=project.version'
  mvn_stdout = shell(mvn_command)

  version_pattern = /^(\d+.*-)(\d+)$/
  if mvn_stdout !~ version_pattern
    puts mvn_stdout
    raise "version number #{version_pattern} not found"
  end
  [$1, $2]
end

if __FILE__ == $0
  version, build =* get_version
  puts "#{version}#{build}"
  # use in scripts with
  # artefact_version=$(./pipeline/get_version.rb)
end
