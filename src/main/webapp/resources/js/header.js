$(document).ready(function () {
    setInterval(refresh, 1000 * 60);
});

function refresh() {
    const cName = "X-AUTH-TOKEN";
    const name = cName + "=";
    const cDecoded = decodeURIComponent(document.cookie);
    const cArr = cDecoded.split('; ');
    let res;
    cArr.forEach(val => {
        if (val.indexOf(name) === 0) res = val.substring(name.length);
    })
    if (res !== undefined) {
        $.ajax({
            type: "GET",
            url: "/refresh",
            success: function () {
            },
            error: function () {
                console.log("error");
            }
        });
    }
    return null;
}