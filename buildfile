# Generated by Buildr 1.4.12, change to your liking


# Version number for this release
VERSION_NUMBER = "1.0.0"
# Group identifier for your projects
GROUP = "SetApp"
COPYRIGHT = "None"

# Specify Maven 2.0 remote repositories here, like this:
repositories.remote << "http://repo1.maven.org/maven2"

desc "The SetApp project"
define "SetApp" do

  project.version = VERSION_NUMBER
  project.group = GROUP
  manifest["Implementation-Vendor"] = COPYRIGHT
end
