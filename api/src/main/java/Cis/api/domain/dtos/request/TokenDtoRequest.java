package Cis.api.domain.dtos.request;

import Cis.api.domain.enums.Roles;

public record TokenDtoRequest(String login, String senha) {
}
