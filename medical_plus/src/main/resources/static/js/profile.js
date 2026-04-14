function toggleCard(card) {
        card.classList.toggle("ativo");
    }

    function irParaAba(aba) {
        window.location.href = "/profile?aba=" + aba;
    }

    window.onload = function () {

        const params = new URLSearchParams(window.location.search);
        const aba = params.get("aba") || "dados";

        document.querySelectorAll('.section').forEach(s => s.classList.remove('active'));
        document.querySelectorAll('.menu-item').forEach(m => m.classList.remove('active'));

        document.getElementById(aba)?.classList.add('active');

        document.querySelectorAll('.menu-item').forEach(m => {
            if (m.innerText.toLowerCase().includes(aba.toLowerCase())) {
                m.classList.add('active');
            }
        });

    };