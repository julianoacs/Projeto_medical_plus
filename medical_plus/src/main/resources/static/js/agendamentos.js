const exameSelect  = document.getElementById('exameSelect');
const medicoSelect = document.getElementById('medicoSelect');
const localSelect  = document.getElementById('localSelect');
const localWrapper = document.getElementById('localWrapper');

// Filtrar médicos por exame
exameSelect.addEventListener('change', () => {
    const exameEscolhido = exameSelect.value;
    const options = medicoSelect.querySelectorAll('option');

    options.forEach(opt => {
        if (opt.value === '') return;
        const examesDoMedico = opt.dataset.exames ? opt.dataset.exames.split(',') : [];
        opt.style.display = examesDoMedico.includes(exameEscolhido) ? 'block' : 'none';
    });

    medicoSelect.value = '';
    localWrapper.style.display = 'none';
    localSelect.innerHTML = '<option value="">-- Selecione o local --</option>';
});

// Mostrar locais ao selecionar médico
medicoSelect.addEventListener('change', () => {
    const optSelecionada = medicoSelect.options[medicoSelect.selectedIndex];
    const locaisRaw = optSelecionada?.dataset?.locais || '';

    localSelect.innerHTML = '<option value="">-- Selecione o local --</option>';

    if (locaisRaw && locaisRaw.trim() !== '') {
        const locais = locaisRaw.split(';').filter(l => l.trim() !== '');
        locais.forEach(l => {
            const [nome, endereco] = l.split('|');
            const opt = document.createElement('option');
            opt.value = nome + (endereco ? ' — ' + endereco : '');
            opt.textContent = nome + (endereco ? ' — ' + endereco : '');
            localSelect.appendChild(opt);
        });
        localWrapper.style.display = 'block';
    } else {
        localWrapper.style.display = 'none';
    }
});

// Validar formulário
function validarForm() {
    if (!medicoSelect.value) {
        alert('Selecione um médico disponível para o exame escolhido!');
        return false;
    }
    return true;
}
