

# **SunShare: Plataforma de Compartilhamento de Créditos de Energia Solar**


## **Integrantes do Grupo**

| Nome                            | RM      |
|---------------------------------|---------|
| Diego Seiti Ogita Iacabo        | 551289  |
| Cristian Alvaro Condori Paucara | 550509  |
| Giovanni Paschoalatto Ibelli    | 98837   |
| Pedro Marques Pais Pavão        | 550252  |
| Luiz Felipe Azevedo de Oliveira | 550348  |

---

## **Descrição do Projeto**  
O **SunShare** é uma plataforma inovadora que promove o compartilhamento de créditos de energia solar entre proprietários de sistemas solares (locadores) e consumidores interessados em reduzir custos e adotar práticas mais sustentáveis. O sistema conecta pessoas que geram excedentes de energia com aquelas que desejam consumir energia limpa, otimizando o uso dos créditos e incentivando a adoção de energias renováveis.

---

### **Objetivo**  
- Facilitar a interação entre locadores e consumidores de energia solar.  
- Conectar geradores de excedentes energéticos a interessados em energia sustentável.  
- Auxiliar na gestão de contratos com termos claros e personalizáveis.  
- Maximizar o uso de créditos excedentes, promovendo benefícios ambientais e econômicos.  

---

## **Funcionalidades Principais**  
**[Veja o vídeo de funcionamento do aplicativo](https://youtu.be/TvB0ZsbT7jg)**  
- https://youtu.be/TvB0ZsbT7jg

---

### 1. **Tela Principal com Navegação**  
- Menu de navegação intuitivo, permitindo acesso às principais funcionalidades:  
  - **Visão Geral**  
  - **Lista de Clientes**  
  - **Créditos Disponíveis**  
  - **Perfil do Usuário**  

### 2. **Tela de Login e Cadastro**  
- Sistema de autenticação integrado ao Firebase Authentication.  
- Cadastro de novos usuários com validação de e-mail e senha.  
- Login seguro com autenticação baseada no Firebase.  

### 3. **Gerenciamento de Clientes**  
- **Cadastro**: Adicionar informações detalhadas como nome, CPF, CEP, energia gerada, energia disponível, distribuidora e consumo médio.  
- **Listagem**: Visualizar clientes vinculados ao usuário logado.  
- **Edição**: Alterar dados diretamente na interface.  
- **Exclusão**: Remover registros com integração ao Firebase Firestore.  

### 4. **Gestão de Créditos**  
- Visualização de créditos de energia solar, ordenados por critérios selecionados (ascendente ou descendente).  
- Detalhamento de créditos disponíveis.  

### 5. **Perfil do Usuário**  
- Exibição de informações do usuário logado, incluindo nome e e-mail.  
- Possibilidade de logout.  

### 6. **Armazenamento e Backend**  
- **Firebase Firestore** para armazenamento eficiente e em tempo real.  

---

## **Tecnologias Utilizadas**  
- **Linguagem**: Kotlin com Jetpack Compose.  
- **UI Framework**: Material 3 Design.  
- **Banco de Dados**: Firebase Firestore.  
- **Autenticação**: Firebase Authentication.  
- **Arquitetura**: MVVM (Model-View-ViewModel).  
- **Imagens**: Coil para carregamento dinâmico.  

---

## **Fluxo de Uso**  
1. **Tela de Login e Cadastro**  
   - Login de usuários existentes ou cadastro de novos usuários.  

2. **Home Page**  
   - Navegação entre funcionalidades através do menu inferior.  

3. **Gestão de Clientes**  
   - Adicionar, listar, editar e excluir clientes registrados no sistema.  

4. **Créditos Disponíveis**  
   - Visualizar e organizar créditos de energia excedentes com base em energia disponível.  

5. **Perfil do Usuário**  
   - Consultar informações pessoais do usuário logado e realizar logout.  

---

## **Como Executar o Projeto**  

### **Pré-requisitos**  
- Android Studio instalado.  
- Conta Firebase configurada com Firestore e Authentication habilitados.  
- Arquivo `google-services.json` configurado no projeto.  

### **Passos para Execução**  

1. **Clone o Repositório**  
   ```bash
   git clone https://github.com/Cristian9655/GS2.git
   ```

2. **Configuração do Firebase**  
   - Configure o Firebase para o projeto Android.  
   - Adicione o arquivo `google-services.json` na pasta `app`.  

3. **Abra o Projeto**  
   - Abra o projeto no Android Studio.  
   - Sincronize as dependências do Gradle.  

4. **Execução**  
   - Execute o projeto em um emulador ou dispositivo físico.  

---
