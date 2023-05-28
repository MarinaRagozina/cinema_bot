function switchToOperator() {
    $.response.replies = $.response.replies || [];
    $.response.replies.push({
        type: "switch"
    });
}