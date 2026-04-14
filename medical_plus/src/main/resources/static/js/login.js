window.addEventListener("pageshow", function () {
        document.querySelectorAll("input").forEach(input => input.value = "");
    });