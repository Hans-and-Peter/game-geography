#!/usr/bin/env ruby
require_relative 'lib/shell.rb'

if ARGV.length == 0
  raise "need to pass artefact_version"
end
artefact_version = ARGV[0]

register_task_cmd = "aws ecs register-task-definition " +
                    "--cli-input-json file://ecs_task_template.json " +
                    "--output text --query 'taskDefinition.revision')"
task_revision = shell(register_task_cmd)

service_version = artefact_version.gsub(/\./, '_')

# TODO templating: task-definition name duplicated with Maven and hardcoded
create_service_cmd = 'aws ecs create-service ' +
    '--service-name "game-geography-#{service_version}" ' +
    '--task-definition "hans-and-peter-game-geography:#{task_revision}" --desired-count 1 ' +
    "--output text --query 'service.serviceName'"
service_name = shell(create_service_cmd)

puts service_name

# export task_revision=$(aws ecs register-task-definition --cli-input-json file://ecs_task_template.json \
# --output text --query 'taskDefinition.revision')
#
# export service_version=$( ruby -e 'puts "'$artefact_version'".gsub(/\./, "_")' )
#
# export serviceName=$( aws ecs create-service --service-name "game-geography-$service_version" \
# --task-definition "hans-and-peter-game-geography:$task_revision" --desired-count 1 \
# --output text --query 'service.serviceName' )

