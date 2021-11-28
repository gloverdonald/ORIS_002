$(document).ready(function () {
    let form = $("#search-user-form")
    let usersList = $("#list")
    form.on('submit', function () {
        let search = form.find("#search").val();
        usersList.empty()
        if (search === '') {
            return false
        }
        $.ajax("/searching-user", {
            method: "POST",
            data: "search=" + search,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            success: function (data) {
                let userDto = JSON.parse(data)
                $("#search").trigger('reset');
                form.find("#search").val("")
                let userTag = $("<div></div>")
                if (userDto.length === 0) {
                    userTag.append("<div class=text> Users not found </div>")
                } else {
                    for (let i = 0; i < userDto.length; i++) {
                        var user1 = userDto[i];
                        if (user1.avatarId != null) {
                            userTag.append(" <li ng-repeat=\"user in ctrl.users\" class=\"list-item\">\n" +
                                "<div>\n" +
                                "<div class=\"light_blue text1\">\n" +
                                "<div class=\"light_blue text\">\n" +
                                "<img class=\"fol-avatar\" alt=\"IMAGE\" src=\"/files/" + user1.avatarId + "\"/>\n" +
                                "</div>\n" +
                                "<div class=\"light_blue text decoration\">\n" +
                                "<a href=\"/user-profile?id=" + user1.id + "\">" + user1.firstName + " " + user1.lastName + "</a>\n" +
                                "</div>\n" +
                                "</div>\n" +
                                "</div>\n" +
                                "</li>")
                        } else {
                            userTag.append("<li ng-repeat=\"user in ctrl.users\" class=\"list-item\">\n" +
                                "<div>\n" +
                                "<div class=\"light_blue text1\">\n" +
                                "<div class=\"light_blue text\">\n" +
                                "<img class=\"fol-avatar\" alt=\"IMAGE\" src=\"/no-avatar.png\"/>\n" +
                                "</div>\n" +
                                "<div class=\"light_blue text decoration\">\n" +
                                "<a href=\"/user-profile?id=" + user1.id + "\">" + user1.firstName + " " + user1.lastName + "</a>\n" +
                                "</div>\n" +
                                "</div>\n" +
                                "</div>\n" +
                                "</li>")
                        }
                    }
                }
                userTag.hide()
                usersList.prepend(userTag)
                userTag.show(100)
                let usersCountSpan = $("#usersCount")
                usersCountSpan.html(1 + parseInt(usersCountSpan.html()))
            }
        })
        return false
    })
})



