package process

@Field Lane lane
@Field ResourceBundle messages

section 5, "image:icons/Lane.png[title=\"Lane\"] $lane.name${lane.actor ? " (${new ActorXRef(process: lane.process, actor: lane.actor).refLink()})" : ''}"
        
newLine()
        
writeWithLineBreaks lane.description ?: "_${messages.getString('descriptionPlaceholder')}_"
        
2.times { newLine() }
        
if(lane.actorFilter) {
   section 6, "icon:filter[] ${messages.getString('actorFilter')}"
   newLine()
   if(lane.actorFilter.description) {
       write "$lane.actorFilter.definitionName: $lane.actorFilter.name:: "
       writeWithLineBreaks lane.actorFilter.description
   }else {
       write "*$lane.actorFilter.definitionName: $lane.actorFilter.name*"
   }
   2.times { newLine() }
}
    