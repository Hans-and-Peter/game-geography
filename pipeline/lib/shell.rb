#!/usr/bin/env ruby

def shell(command)
  stdout = `#{command}`

  error_code = $?.exitstatus
  if error_code != 0
    puts command
    puts stdout
    raise "#{command} exit with error #{error_code}"
  end

  stdout
end
