This is the README of SuperWheat
Thanks to thescreem for the original plugin!
Thanks for using!
For support visit the old forum thread: http://bit.ly/superwheatthread
or the new dev.bukkit.org page: http://bit.ly/superwheatpage

This plugin is released under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported (CC BY-NC-SA 3.0) license.

Standard config:

# For help please either refer to the
# forum thread: http://bit.ly/superwheatthread
# or the bukkit dev page: http://bit.ly/superwheatpage
message: §6[SuperWheat] That plant isn't fully grown yet!
creative:
  dropsCreative: false
  blockCreativeDestroying: false
wheat:
  enabled: true
  trampling: true
  delayHit: 3
  water:
    delay: 5
    drops:
      wheat: true
      seed: false
    prevent:
      premature: true
      mature: false
  piston:
    delay: 5
    drops:
      wheat: true
      seed: false
    prevent:
      premature: true
      mature: false
netherWart:
  enabled: true
  delayHit: 3
  water:
    delay: 5
    drops:
      netherWart: true
    prevent:
      premature: true
      mature: false
  piston:
    delay: 5
    drops:
      netherWart: true
    prevent:
      premature: true
      mature: false
cocoaPlant:
  enabled: true
  delayHit: 3
  water:
    delay: 5
    drops:
      cocoaPlant: true
    prevent:
      premature: true
      mature: false
  piston:
    delay: 5
    drops:
      cocoaPlant: true
    prevent:
      premature: true
      mature: false

Permissions (if no permissions system is detected, only OPs are able to use the permissions!)
Only bukkit's permissions system is supported!

SuperWheat.*
Description: Includes all permissions

SuperWheat.wheat.*
Description: Includes all permissions for wheat

SuperWheat.wheat.regrowing
Description: Automatically re-grows the harvested wheat

SuperWheat.wheat.destroying
Description: Allows you to destroy not full grown crops

SuperWheat.wheat.seeds
Description: Drops seeds, too

SuperWheat.netherwart.*
Description: Includes all permissions for nether wart

SuperWheat.netherwart.regrowing
Description: Automatically re-grows the harvested nether wart

SuperWheat.netherwart.destroying
Description: Allows you to destroy not full grown plant

SuperWheat.cocoaplant.*
Description: Includes all permissions for cocoa (plant)

SuperWheat.cocoaplant.regrowing
Description: Automatically re-grows the harvested cocoa

SuperWheat.cocoaplant.destroying
Description: Allows you to destroy not full grown plant