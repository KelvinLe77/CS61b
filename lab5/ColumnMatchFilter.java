/**
 * TableFilter to filter for entries whose two columns match.
 *
 * @author Matthew Owen
 */
public class ColumnMatchFilter extends TableFilter {

    public ColumnMatchFilter(Table input, String colName1, String colName2) {
        super(input);
        // FIXME: Add your code here.
        this.iInput = input;
        this.iColName1 = colName1;
        this.iColName2 = colName2;
    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        Table.TableRow row = candidateNext();
        return row.getValue(iInput.colNameToIndex(iColName1)).equals(row.getValue(iInput.colNameToIndex(iColName2)));
    }

    // FIXME: Add instance variables?
    private Table iInput;
    private String iColName1;
    private String iColName2;
}
