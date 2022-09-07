/**
 *
 * @deprecated This class will be removed in future Bonita version, you may replace its usage using the 
 * groovy language features for those functions.
 * @since 7.12.0
 */
@Deprecated
public class BonitaTypes {

    /**
     * @deprecated Use <pre>table.collectEntries()</pre> instead
     * @since 7.12.0
     */
    @Deprecated
	public static Map<Object,Object> toMap(List<List<Object>> table) {
		def res = [:];
		table.each {
			res.put it[0], it[1]
		};
		res
	}
	
     /**
     * @deprecated Use <pre>map.collect{ key, value -> [key, value] }</pre> instead
     * @since 7.12.0
     */
    @Deprecated
	public static List<List<Object>> toTable(Map<?,?> map) {
		map.collect { key, value ->
			[key, value]
		}
	}


}