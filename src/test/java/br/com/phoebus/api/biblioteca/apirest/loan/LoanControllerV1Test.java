package br.com.phoebus.api.biblioteca.apirest.loan;


import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.loan.service.DeleteLoanService;
import br.com.phoebus.api.biblioteca.apirest.loan.service.EditLoanService;
import br.com.phoebus.api.biblioteca.apirest.loan.service.GetLoanService;
import br.com.phoebus.api.biblioteca.apirest.loan.service.ListLendService;
import br.com.phoebus.api.biblioteca.apirest.loan.service.ListPageLendService;
import br.com.phoebus.api.biblioteca.apirest.loan.service.SaveLoanService;
import br.com.phoebus.api.biblioteca.apirest.loan.v1.LoanControllerV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static br.com.phoebus.api.biblioteca.apirest.loan.builders.LoanDTOBuilder.createLoanDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(LoanControllerV1.class)
@DisplayName("Valida a funcionalidade do controller de Loan")
public class LoanControllerV1Test {

    private final String URI_LOAN = "/v1/loan";
    private final String CONT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeleteLoanService deleteLoanService;
    @MockBean
    private EditLoanService editLoanService;
    @MockBean
    private GetLoanService getLoanService;
    @MockBean
    private ListPageLendService listPageLendService;
    @MockBean
    private ListLendService listLendService;
    @MockBean
    private SaveLoanService saveLoanService;

    @Test
    @DisplayName("Deleta um emprestimo")
    void shouldDeleteLoanForID() throws Exception {

        mockMvc.perform(delete(URI_LOAN + "/{id}", 1L)
                .contentType(CONT_TYPE))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteLoanService).delete(1L);
    }

    @Test
    @DisplayName("Edita emprestimo")
    void shouldEditLoan() throws Exception {

        Long id = 1L;
        LoanDTO loanDTO = createLoanDTO().id(1L).build();
        loanDTO.setLoanTime("Abril");

        mockMvc.perform(put(URI_LOAN + "/{id}", 1L)
                .contentType(CONT_TYPE)
                .content(objectMapper.writeValueAsString(loanDTO)))
                .andDo(print())
                .andExpect(status().isNoContent());

        ArgumentCaptor<LoanDTO> captorLoanDTO = ArgumentCaptor.forClass(LoanDTO.class);
        ArgumentCaptor<Long> captorLong = ArgumentCaptor.forClass(Long.class);
        verify(editLoanService).edit(captorLong.capture(), captorLoanDTO.capture());
        LoanDTO result = captorLoanDTO.getValue();

        assertThat(captorLong.getValue()).isEqualTo(id);
        assertThat(result.getId()).isEqualTo(loanDTO.getId());
        assertThat(result.getLoanTime()).isEqualTo(loanDTO.getLoanTime());
        assertThat(result.getBooks().size()).isEqualTo(loanDTO.getBooks().size());
        assertThat(result.getBooks().get(0).getId()).isEqualTo(loanDTO.getBooks().get(0).getId());
        assertThat(result.getBooks().get(0).getResume()).isEqualTo(loanDTO.getBooks().get(0).getResume());
        assertThat(result.getBooks().get(0).getAuthor()).isEqualTo(loanDTO.getBooks().get(0).getAuthor());
        assertThat(result.getBooks().get(1).getId()).isEqualTo(loanDTO.getBooks().get(1).getId());
        assertThat(result.getBooks().get(1).getResume()).isEqualTo(loanDTO.getBooks().get(1).getResume());
        assertThat(result.getBooks().get(1).getTitle()).isEqualTo(loanDTO.getBooks().get(1).getTitle());

    }

    @Test
    @DisplayName("Pesquisa emprestimo por ID")
    void shouldReturnLoanForID() throws Exception {

        LoanDTO loanDTO = createLoanDTO().id(1L).build();
        when(getLoanService.getLoan(anyLong())).thenReturn(loanDTO);

        MvcResult mvcResult = mockMvc.perform(get(URI_LOAN + "/{id}", 1L)
                .contentType(CONT_TYPE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultReponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(loanDTO))
                .isEqualToIgnoringWhitespace(resultReponseBody);
    }

    @Test
    @DisplayName("Pesquisa emprestimo que não existe e lança exceção")
    void shouldExceptionNotFoundLoanForID() throws Exception {

        when(getLoanService.getLoan(anyLong())).thenThrow(new LoanNotFoundException());

        mockMvc.perform(get(URI_LOAN + "/{id}", 1L)
                .contentType(CONT_TYPE))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getLoanService).getLoan(1L);
    }

    @Test
    @DisplayName("Traz a lista de emprestmos")
    void shouldListLend() throws Exception {

        LoanDTO loan1 = createLoanDTO().build();
        LoanDTO loan2 = createLoanDTO().build();
        LoanDTO loan3 = createLoanDTO().build();
        LoanDTO loan4 = createLoanDTO().build();

        when(listLendService.listLend()).thenReturn(Arrays.asList(loan1, loan2, loan3, loan4));

        MvcResult mvcResult = mockMvc.perform(get(URI_LOAN)
                .contentType(CONT_TYPE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(Arrays.asList(loan1, loan2, loan3, loan4)))
                .isEqualToIgnoringWhitespace(resultResponseBody);

        verify(listLendService).listLend();
    }

    @Test
    @DisplayName("Salva um emprestimo")
    void shuldSaveLoan() throws Exception {

        LoanDTO loanDTO = createLoanDTO().id(1L).build();

        mockMvc.perform(post(URI_LOAN)
                .contentType(CONT_TYPE)
                .content(objectMapper.writeValueAsString(loanDTO)))
                .andDo(print())
                .andExpect(status().isCreated());

        ArgumentCaptor<LoanDTO> captorLoanDTO = ArgumentCaptor.forClass(LoanDTO.class);
        verify(saveLoanService).save(captorLoanDTO.capture());
        LoanDTO result = captorLoanDTO.getValue();

        assertThat(result.getId()).isEqualTo(loanDTO.getId());
        assertThat(result.getLoanTime()).isEqualTo(loanDTO.getLoanTime());
        assertThat(result.getBooks().size()).isEqualTo(loanDTO.getBooks().size());
        assertThat(result.getBooks().get(0).getId()).isEqualTo(loanDTO.getBooks().get(0).getId());
        assertThat(result.getBooks().get(0).getResume()).isEqualTo(loanDTO.getBooks().get(0).getResume());
        assertThat(result.getBooks().get(0).getAuthor()).isEqualTo(loanDTO.getBooks().get(0).getAuthor());
        assertThat(result.getBooks().get(1).getId()).isEqualTo(loanDTO.getBooks().get(1).getId());
        assertThat(result.getBooks().get(1).getResume()).isEqualTo(loanDTO.getBooks().get(1).getResume());
        assertThat(result.getBooks().get(1).getTitle()).isEqualTo(loanDTO.getBooks().get(1).getTitle());
    }
}
