/**
 * TableFilter to filter for containing substrings.
 *
 * @author Matthew Owen
 */

public class SubstringFilter extends TableFilter {

    public SubstringFilter(Table input, String colName, String subStr) {
        super(input);
        // FIXME: Add your code here.
        this.iInput = input;
        this.iColName = colName;
        this.iSubStr = subStr;
    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        Table.TableRow row = candidateNext();
        return row.getValue(iInput.colNameToIndex(iColName)).contains(iSubStr);
    }

    // FIXME: Add instance variables?
    private Table iInput;
    private String iColName;
    private String iSubStr;
}
