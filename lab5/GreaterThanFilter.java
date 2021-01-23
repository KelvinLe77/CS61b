/**
 * TableFilter to filter for entries greater than a given string.
 *
 * @author Matthew Owen
 */

public class GreaterThanFilter extends TableFilter {

    public GreaterThanFilter(Table input, String colName, String ref) {
        super(input);
        // FIXME: Add your code here.
        this.iInput = input;
        this.iColName = colName;
        this.iRef = ref;
    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        Table.TableRow row = candidateNext();
        int diff = row.getValue(iInput.colNameToIndex(iColName)).compareTo(iRef);
        if (diff > 0) {
            return true;
        }
        return false;
    }

    // FIXME: Add instance variables?
    private Table iInput;
    private String iColName;
    private String iRef;
}
