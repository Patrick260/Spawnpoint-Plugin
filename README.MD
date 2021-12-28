## What's this?
A simple Spawnpoint Plugin for spigot! Works with the Minecraft versions 1.8.x to 1.18.x.

## How to use it
Download the latest release and put the jar file into your plugin folder, then restart the server.

Commands:

    /setspawnpoint <world, x, y, z> (yaw, pitch) - Set the spawnpoint.
    /spawn - Teleports you to the spawn.
    /spawnpointplugin-info - Display infos about the plugin.
    /spawnpointplugin-reloadconfig - Reload the config.yml

Permissions:\
Attention, these are the default permissions. You can change them in the config.yml to whatever you want.

    spawnpointPlugin.commands.admin.setSpawnpoint - The permissions is needed to set the spawnpoint.
    spawnpointPlugin.commands.spawn.teleport - The permission is needed to teleport you to the spawnpoint.
    spawnpointPlugin.commands.admin.reloadConfig - The permission is nedded to reload the config.

## Features
- Set a spawnpoint where player spawn when they join, die or do /spawn
- Multiple language supported

## Dev information's
JDK: adobe-openj9-1.8.0_302\
API: spigot-1.8.8
