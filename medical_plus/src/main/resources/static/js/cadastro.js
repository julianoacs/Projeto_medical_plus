window.addEventListener("pageshow", function () {
        document.querySelectorAll("input").forEach(input => input.value = "");
        document.querySelectorAll("select").forEach(select => select.selectedIndex = 0);
    });