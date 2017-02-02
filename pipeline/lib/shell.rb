#!/usr/bin/env ruby

def shell(command)
  STDERR.puts command
  stdout = `#{command}`

  error_code = $?.exitstatus
  if error_code != 0
    STDERR.puts error_code
    STDERR.puts stdout
    raise "#{command} exit with error #{error_code}"
  end

  stdout
end
