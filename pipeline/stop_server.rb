#!/usr/bin/env ruby
require_relative 'lib/shell.rb'

if ARGV.length == 0
  raise "need to pass instance_id"
end
instance_id = ARGV[0]

stop_server_cmd = "aws ec2 terminate-instances --instance-ids \"#{instance_id}\""
shell(stop_server_cmd)

# aws ec2 terminate-instances --instance-ids "$instance_id" > dev/nul
