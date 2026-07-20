const token =
    localStorage.getItem(
        "token"
    );

const role =
    localStorage.getItem(
        "role"
    );

if (
    !token ||
    role !== "USER"
) {
    alert(
        "Unauthorized"
    );

    window.location.href =
        "index.html";
}
