/*METADATA
Map<Object,Object> toMap(List<List<Object>> table)=Converts a table (output of widget or connector) into a Map
List<List<Object>> toTable(Map<?,?> map)=Converts a map into a table, to be the output of a Table widget or of a connector Array input
*/
public class BonitaTypes {

	public static Map<Object,Object> toMap(List<List<Object>> table) {
		def res = [:];
		table.each {
			res.put it[0], it[1]
		};
		res
	}
	
	public static List<List<Object>> toTable(Map<?,?> map) {
		map.collect { key, value ->
			[key, value]
		}
	}


}