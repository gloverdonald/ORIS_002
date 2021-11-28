$(document).ready(function () {
    let form = $("#add-post-form")
    let postsList = $("#post-list")


    form.on('submit', function () {
        let content = form.find("#content").val();
        if (content === '') {
            return false
        }

        $.ajax("/add-post", {

            method: "POST",
            data: "content=" + content,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            success: function (data) {
                let postDto = JSON.parse(data)
                let pid = postDto.id
                var paramsString = document.location.pathname
                console.log(paramsString === "/feed")
                form.find("#content").val("")
                let postTag = $("<div class=post-" + pid + "></div>")
                let options = {year: "numeric", month: "long", day: "numeric", hour: "numeric", minute: "numeric"}
                postTag.append("<div class=\"light_blue \">" + new Date(postDto.createdAt).toLocaleString('ru-RU', options) + "</div>")
                if (paramsString === "/feed") {
                    postTag.append("<div class=\"text \">Автор: <a class=\"authorName decoration\" href=\"/user-profile?id="+ postDto.author.id+"\"> " + postDto.author.firstName + "</a></div>")
                } else {
                    postTag.append("<div class=\"text \">Автор: " + postDto.author.firstName + "</div>")
                }
                postTag.append("<div class=\"text \">" + postDto.content + "</div>")

                postTag.append("<input class=\"deleteButton \" name=\"delid\" value=\"delete\" type=\"submit\" onclick=\"postDelete(" + pid + ")\"></div>")
                postTag.append(" <div class=\"divider\"></div>")
                postTag.hide()
                postsList.prepend(postTag)
                postTag.show(300)
                let postsCountSpan = $("#postsCount")
                postsCountSpan.html(1 + parseInt(postsCountSpan.html()))
            }
        })
        return false
    })

})


function postDelete(profileId) {
    console.log(profileId)
    $.ajax("/delete?delete=" + profileId, {
        method: 'DELETE',

        success: function (data) {
            console.log(profileId)
            let buttons = $('.post-' + profileId)
            let postsCountSpan = $("#postsCount")
            postsCountSpan.html(parseInt(postsCountSpan.html()) - 1)
            buttons.html("")
        }
    })
}


