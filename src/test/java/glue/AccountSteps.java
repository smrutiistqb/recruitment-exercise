package glue;

import account.Account;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class AccountSteps {

    Account account = null;

    @Given("^Account exists for Acc No\\. \"([^\"]*)\" with Name \"([^\"]*)\"$")
    public void accountExistsForAccNoWithName(String number, String name) {
        account = new Account(number, name);
    }

    @And("deposits are made")
    public void depositsAreMade(DataTable dataTable) {
        // Iterate over the DataTable to get transaction details and perform deposits
        List<List<String>> data = dataTable.asLists(String.class);

        for (List<String> row : data) {
            String txnId = row.get(0);
            Double amount = Double.parseDouble(row.get(1));
            account.deposit(txnId, amount);
        }
    }

    @And("withdrawals are made")
    public void withdrawalsAreMade(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        for (List<String> row : data) {
            String txnId = row.get(0);
            Double amount = Double.parseDouble(row.get(1));
            account.withdraw(txnId, amount);
        }
    }

    @When("statement is produced")
    public void produceStatement() {
    }

    @Then("statement includes Name : {string} and Account : {string}")
    public void statementIncludesNameAndAcc(String name, String acc) {
        account.includesAccountDetails(name, acc);

    }

    @Then("statement includes Balance :{string}")
    public void statementIncludesBalance(String balance) {
        account.includesBalance(Double.parseDouble(balance));

    }

    @Then("statement includes Transaction : {string}")
    public void statementIncludesTransaction(String transaction) {
        account.includesTransactionDetails(List.of(transaction));

    }

    @Then("statement includes {string}")
    public void statementIncludesTxns(String transaction) {
        account.includesTransactionDetails(List.of(transaction));

    }


}

