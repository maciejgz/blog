package pl.mg.blog.comment.application.model;

public enum CommentSortValues {

    CREATED("created");

    public String getColumn() {
        return column;
    }

    private final String column;

    CommentSortValues(String column) {
        this.column = column;
    }

    public static boolean contains(String value) {
        for (CommentSortValues commentSortValue : CommentSortValues.values()) {
            if (commentSortValue.column.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
