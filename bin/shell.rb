#!/usr/bin/env ruby

def shell(command)
  puts command
  stdout = `#{command}`

  error_code = $?.exitstatus
  if error_code != 0
    puts stdout
    raise "#{command} exit with error #{error_code}"
  end

  stdout
end
