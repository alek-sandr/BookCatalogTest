//--- authors.jsp code --------------------------------------------------------
$(".delete_author").click(function(event) {
    event.preventDefault();
    var id = $(this).closest("tr").attr("id");
    deleteEntity(id, "authors/deleteAuthor");
});

//--- books.jsp code --------------------------------------------------------
$(".delete_book").click(function(event) {
    event.preventDefault();
    var id = $(this).closest("tr").attr("id");
    deleteEntity(id, "books/deleteBook");
});

function deleteEntity(id, actionUrl) {
    var entity = new Object();
    entity.id = id;

    $.ajax({
        url: actionUrl,
        type: "POST",
        dataType: "json",
        data: JSON.stringify(entity),
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
            if (data.success) {
                $("#" + id).remove();
            } else {
                alert(data.message);
            }
        },
        error:function(data, status, er) {
            alert(data.responseText);
        }
    });
}
