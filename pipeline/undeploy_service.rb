#!/usr/bin/env ruby
require_relative 'lib/shell.rb'

if ARGV.length == 0
  raise "need to pass service_name"
end
service_name = ARGV[0]

remove_task_cmd = "aws ecs update-service --service \"#{service_name}\" --desired-count 0"
shell(remove_task_cmd)

delete_service_cmd = "aws ecs delete-service --service \"#{service_name}\""
shell(delete_service_cmd)

# aws ecs update-service --service "${serviceName}" --desired-count 0 > dev/nul
# aws ecs delete-service --service "${serviceName}" > dev/nul
