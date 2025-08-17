# Projeto Conta Bancária (Mobile) – DS III

**Autor:** Bruno Correa

App Android simples para demonstrar funcionalidades de uma **conta bancária**:
- Depositar
- Sacar
- Ver saldo
- Feedback com **vibração** em erro (recurso do dispositivo)

## Como rodar
1. Abrir `android-app` no **Android Studio** (JDK 17+).
2. Emulador Android ou celular com Depuração USB.
3. Run ▶️ (MainActivity).

## Estrutura
- `BankAccount.kt` – regra de negócio (depositar/sacar/saldo).
- `MainActivity.kt` – UI e integração com vibração.
- `activity_main.xml` – layout com campos e botões.

## Observações
- O projeto atende à Agenda 02: **codificação mobile**, **ambiente Android Studio** e **recurso do dispositivo (vibração)**.
- Sem vídeo: o repositório contém **código** e pode incluir **prints da IDE** na pasta `docs/` se necessário.
