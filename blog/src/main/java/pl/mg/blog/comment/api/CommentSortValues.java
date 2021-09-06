package pl.mg.blog.comment.api;

public enum CommentSortValues {

    CREATED("created");

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
