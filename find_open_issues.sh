#!/usr/bin/env bash
grep -r -i --include \*.java --include \*.xml --include \*.txt --include \*.properties --include Dockerfile --exclude workspace.xml TODO .
