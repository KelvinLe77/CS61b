/**
 * TableFilter to filter for entries equal to a given string.
 *
 * @author Matthew Owen
 */
public class EqualityFilter extends TableFilter {

    public EqualityFilter(Table input, String colName, String match) {
        super(input);
        // FIXME: Add your code here.
        this.iInput = input;
        this.iColName = colName;
        this.iMatch = match;
    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        Table.TableRow row = candidateNext();
        return row.getValue(iInput.colNameToIndex(iColName)).equals(iMatch);

    }

    // FIXME: Add instance variables?
    private Table iInput;
    private String iColName;
    private String iMatch;
}
