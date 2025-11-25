# Projeto_CIS_Rascunhos

```mermaid
erDiagram
    Psicologo ||--o{ Coordenacao : "(1,1) para (0,n)"
    Coordenacao ||--|| Psicologo : "(1,1) para (1,1)"

    Psicologo ||--o{ Sessao : "(1,1) para (0,n)"
    Coordenacao ||--|| Sessao : "(1,1) para (0,n)"

    Sessao ||--|| Coordenacao : "(0,n) para (1,1)"
    Sessao ||--|| Psicologo : "(0,n) para (1,1)"
    Sessao ||--|| Paciente : "(1,1) para (0,n)"
    
    Coordenacao ||--|| Paciente : "(1,1) para (0,n)"
    
    Usuario ||--o{ Paciente : "(1,1) para (0,n)"
    
    
    Psicologo {
        id_psicologo PK
        id_Coordenacao FK
        Nome 
        Matricula
        Ativo
    }

    Coordenacao {
        id_coordenacao PK
        Nome
        Matricula
        Cargo
        Ativo
    }

    Sessao {
        id_sessao PK
        id_Coordenacao FK
        id_Psicologo FK
        id_Usuario FK
        Horario_Confirmado
        Ativo
    }

    Paciente {
        id_usuario PK
        id_Coordenacao FK
        id_Usuario FK
        Nome
        Telefone
        Disponibilidade_Data
        Disponibilidade_Horario
        Data_Registro
    }

    Usuario {
        id_usuario PK
        Login
        Senha
        Permissao
    }

```

# Dashboard Psicologia

**Description**: Sistema de gestão para psicólogos com calendário mensal interativo de horários disponíveis dos pacientes. Design amigável e colorido com interface responsiva.

**Tech Stack**: React + javascript + Vite | Backend: N/A | Auth: N/A

## Directory Structure
- `/src`: Código frontend
  - `/components`: Componentes React 
  - `/components`: `/calendar`: Calendar components (MonthlyCalendar.jsx, PotentialPatientsGrid.jsx)
  - `/components`: `/common`: Common components (Icons.jsx, PatientDetailSidebar.jsx)
  - `/components`:`/layouts`: Sidebar component (Sidebar.jsx)
  - `/data`: Dados mock (mockData.ts)
  - `/utils`: Definições (dataUtil.js)
  - `/view`: Dashboard overview (DashbooardView.jsx)

## Features and Future

### Implementado
1. **Sidebar de Navegação Esquerda**: Menu com 3 opções (Calendário Semanal, Meus Pacientes, Disponibilidade)
2. **Calendário Semanal Interativo**: Visualização de 7 dias com slots de horários (8h-18h), navegação entre semanas, destaque do dia atual, clique em horários para ver detalhes
3. **Lista de Pacientes**: Visualização em grid com busca, filtros (todos/ativos/inativos), cards coloridos com informações completas
4. **Gestão de Disponibilidade**: Interface para psicólogo configurar horários disponíveis/bloqueados por dia da semana
5. **Cards de Estatísticas**: 4 cards com métricas (Total de Pacientes, Pacientes Ativos, Agendamentos Pendentes, Taxa de Ativos)
6. **Sidebar Direita de Detalhes**: Painel lateral que aparece ao clicar em paciente/horário, mostrando sessões, contatos, próxima consulta, horários disponíveis
7. **Status de Agendamentos**: Visualização com cores (verde para confirmado, amarelo para pendente)
8. **Design Responsivo**: Layout de 3 painéis adaptável para mobile, tablet e desktop

### Limitações Conhecidas
- Dados são mock/estáticos (não há persistência real)
- Não há autenticação de usuário
- Disponibilidade não afeta calendário real

## Database Schema
**Type**: N/A (usando dados mock)

## Deno Functions
N/A

## API Endpoints
N/A (aplicação frontend pura com dados mock)

## Improvement Opportunities

### Alta Prioridade
- [ ] Adicionar filtros por status de agendamento (confirmado/pendente)
- [ ] Implementar sistema de notificações para consultas próximas
- [ ] Adicionar funcionalidade de criar/editar/deletar agendamentos

### Média Prioridade
- [ ] Integrar com backend real para persistência de dados
- [ ] Adicionar sistema de autenticação para múltiplos psicólogos
- [ ] Implementar visualização mensal do calendário

### Baixa Prioridade / Melhorias Futuras
- [ ] Exportar relatórios de sessões em PDF
- [ ] Sistema de lembretes por email/SMS
- [ ] Histórico de sessões com anotações
