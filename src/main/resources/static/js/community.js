
/**
 * 提交回复
 */
function post_comment() {
    var questionId = $("#question_id").val();
    var commentTextarea = $("#comment_content");
    var content = commentTextarea.val();
    commentTextarea.val("");
    comment2target(questionId, 1, content);
}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code === 200) {
                //window.location.reload();
            } else {
                alert(response.message);
                /* if (response.code == 2003) {
                     var isAccepted = confirm(response.message);
                     if (isAccepted) {
                         window.open("https://github.com/login/oauth/authorize?client_id=2859958f9f059979ed3a&redirect_uri=" + document.location.origin + "/callback&scope=user&state=1");
                         window.localStorage.setItem("closable", true);
                     }
                 } else {
                     alert(response.message);
                 }*/
            }
        },
        dataType: "json"
    });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    commentInput = $("#input-" + commentId);
    var content = commentInput.val();
    commentInput.val("");
    comment2target(commentId, 2, content);
}

function test() {
    alert(123);
}

/*
<div class="questionComment media">
    <div class="media-left">
            <img class="media-object" th:src="${comment.user.avatarUrl}">
    </div>
    <div class="media-body">
        <h5 class="media-heading">
            <span th:text="${comment.user.name}"></span>
        </h5>
        <span th:text="${comment.content}"></span>
        <div class="comment-operator">
            <a><span class="glyphicon glyphicon-thumbs-up"></span>赞</a>
            <a th:href="'#collapse-'+${comment.id}" aria-expanded="false" class="collapsed" is-loaded="0"
               data-toggle="collapse" th:data-cid="${comment.id}" onclick="emergeSubComments(this)"
            >
                <span class="glyphicon glyphicon-comment"></span>
                回复
                <span th:id="'subCommentCount-'+${comment.id}" th:text="${comment.commentCount}"></span>
            </a>
            <!--二级评论隐藏域-->
            <div class="sub-comments collapse" th:id="'collapse-'+${comment.id}">

                <div class="sub-comment-form">
                    <input th:id="'input-'+${comment.id}" class="form-control" placeholder="评论一下">
                    <button type="button" class="btn btn-success" th:data-id="${comment.id}" onclick="comment(this)">回复</button>
                </div>
            </div>
            <span class="text-desc" style="float: right!important;"><span
                    th:text="${#dates.format(comment.gmtCreate,'yyyy-MM-dd HH:mm')}"></span></span>
        </div>
    </div>
</div>
*/
function createQuestionComment(comment) {
    //媒体对象左侧
    var mediaLeftElement = $("<div/>", {
        "class": "media-left"
    }).append($("<img/>", {
        "class": "media-object img-rounded",
        "src": comment.user.avatarUrl
    }));

    //评论按钮
    var commetOperator = $("<a/>",{
        "href":"#collapse-"+comment.id,
        "aria-expanded":"false",
        "class":"collapsed",
        "is-loaded":"0",
        "data-toggle":"collapse",
        "data-cid":comment.id,
        "onclick":"emergeSubComments(this)"
    }).append($("<span/>"),{
        class:"glyphicon glyphicon-comment"
    }).append("回复").append($("<span/>"),{
        "id":"subCommentCount-"+comment.id,
        "html":comment.commentCount
    });

    //collapse
    var collapse = $("<div/>",{
        "class":"sub-comments collapse",
        "id":"collapse-"+comment.id
    });

    //按钮操作区
    var operatorArea = $("<div/>",{
        "class":"comment-operator",
    }).append(
        $("<a/>").append($("<span/>",{
        "class":"glyphicon glyphicon-thumbs-up"
    })).append("赞")).append(commetOperator).append(collapse).append($("<span/>",{
        "class":"text-desc",
        "style":"float: right!important;",
        "html":moment(comment.gmtCreate).format('yyyy-MM-dd HH:mm')
    }));

    //媒体主体
    var mediaBodyElement = $("<div/>", {
        "class": "media-body"
    }).append($("<h5/>", {
        "class": "media-heading",
        "html": comment.user.name
    })).append($("<span/>", {
        "html": comment.content
    })).append(operatorArea);

    var media = $("<div/>",{
        "class":"questionComment media"
    }).append(mediaLeftElement).append(mediaBodyElement);

    return media;

}

/*
    <div class="sub-comment-reply media">
        <div class="media-left">
            <a href="#">
                <img class="media-object" src="">
            </a>
        </div>
        <div class="media-body">
            <h4 class="media-heading">

            </h4>
            <span text=""></span>
        </div>
    </div>*/
function createSubComment(comment) {
    var mediaLeftElement = $("<div/>", {
        "class": "media-left"
    }).append($("<img/>", {
        "class": "media-object img-rounded",
        "src": comment.user.avatarUrl
    }));

    var mediaBodyElement = $("<div/>", {
        "class": "media-body"
    }).append($("<h4/>", {
        "class": "media-heading",
        "html": comment.user.name
    })).append($("<div/>", {
        "html": comment.content
    })).append($("<div/>", {
        "class": "menu"
    }).append($("<span/>", {
        "class": "pull-right",
        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
    })));

    var mediaElement = $("<div/>", {
        "class": "sub-comment-reply media"
    }).append(mediaLeftElement).append(mediaBodyElement);
    return mediaElement;
}

function emergeSubComments(e) {
    var isLoad = e.getAttribute("is-loaded");
    //第一次展开二级评论
    if (isLoad === "0") {
        var cid = e.getAttribute("data-cid");
        var collapse = $("#collapse-" + cid);

        $.getJSON("/comment/" + cid, function (response) {
            var comments = response.data;
            if (comments != null) {
                $.each(comments, function (index, comment) {
                    var mediaElement = createSubComment(comment);

                    collapse.prepend(mediaElement);
                });
            }
            e.setAttribute("is-loaded", "1");
        });
    }
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}