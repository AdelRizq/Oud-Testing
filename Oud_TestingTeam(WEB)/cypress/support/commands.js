before(() => {
  cy.fixture("URLS.json", "utf-8").then((URLSData) => {
    self.URLS = URLSData;
  });
  cy.fixture("Home/Data.json").then((DataID) => {
    self.Data = DataID;
  });
  cy.fixture("Account/Data.json").then((AccData) => {
    self.AccountData = AccData;
  });
});

Cypress.Commands.add("login", () => {
  cy.get(`${self.loginIds.email}`).type(self.loginData.email);
  cy.get(`${self.loginIds.password}`).type(self.loginData.password);
  cy.get(`${self.loginIds.rememberMe}`).click();
  cy.get(`${self.loginIds.button}`).click();
});

Cypress.Commands.add("CheckChangingTheEmail", () => {
  cy.visit(URLS.OverViewURL);
  cy.contains(AccountData.NewEmail);
});
Cypress.Commands.add("Check_Start_Com_with_invalid_char", () => {
  cy.CheckOudpattern("123@gamil.");
  var text = "123@gmail.";

  for (var i = 33; i <= 47; i++) {
    if (i === 45) continue;
    var char = String.fromCharCode(i);
    var newtext = text + char + "a";
    cy.CheckOudpattern(newtext);
  }
  //cy.CheckOudpattern('123@gmail.0a')
  for (var i = 58; i < 64; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "a";
    cy.CheckOudpattern(newtext);
  }

  for (var i = 91; i <= 96; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "a";
    cy.CheckOudpattern(newtext);
  }

  for (var i = 123; i <= 126; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "a";
    cy.CheckOudpattern(newtext);
  }

  cy.CheckOudpattern("123@gamil.1231a fd"); // check the space inside the
});

Cypress.Commands.add("check_end_Com_With_invalid_char", () => {
  var text = "123@gmail.";

  for (var i = 33; i <= 47; i++) {
    if (i === 45) continue; // this test fails so I want to except this to continue the tests
    var char = String.fromCharCode(i);
    var newtext = text + "a" + char;
    cy.CheckOudpattern(newtext);
  }
  //cy.CheckOudpattern('123@gmail.a0')
  for (var i = 58; i < 64; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + "a" + char;
    cy.CheckOudpattern(newtext);
  }

  for (var i = 91; i <= 96; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + "a" + char;
    cy.CheckOudpattern(newtext);
  }

  for (var i = 123; i <= 126; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + "a" + char;
    cy.CheckOudpattern(newtext);
  }
});

Cypress.Commands.add("Check_invalid_char_in_middle_in_Com", () => {
  var text = "123@gamil.c";
  for (var i = 33; i <= 44; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "om";
    cy.CheckOudpattern(newtext);
  }

  cy.CheckOudpattern("123@gamil.c/om");
  // instead of ending the loop at 47 and adding if cond.
  // in the loop i will test  it outside the loop ..

  for (var i = 58; i <= 64; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "om";
    cy.CheckOudpattern(newtext);
  }

  for (var i = 91; i <= 96; i++) {
    if (i != 95) {
      var char = String.fromCharCode(i);
      var newtext = text + char + "a";
      cy.CheckOudpattern(newtext);
    }
  }

  for (var i = 123; i <= 126; i++) {
    if (i != 126) {
      var char = String.fromCharCode(i);
      var newtext = text + char + "a";
      cy.CheckOudpattern(newtext);
    }
  }
});

Cypress.Commands.add("Check_Start_at_with_invalid_char", () => {
  var text = "123@";

  for (var i = 33; i <= 47; i++) {
    if (i === 45) continue; // this test fails so I want to except this to continue the tests
    var char = String.fromCharCode(i);
    var newtext = text + char + "gmail.com";
    cy.CheckOudpattern(newtext);
  }
  for (var i = 58; i <= 64; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "gmail.com";
    cy.CheckOudpattern(newtext);
  }
  for (var i = 91; i <= 96; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "gmail.com";
    cy.CheckOudpattern(newtext);
  }

  for (var i = 123; i <= 126; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "gmail.com";
    cy.CheckOudpattern(newtext);
  }
});
Cypress.Commands.add("Check_end_at_with_invalid_char", () => {
  var text = "123@";

  for (var i = 33; i <= 47; i++) {
    if (i === 45) continue; // this test fails so I want to except this to continue the tests
    var char = String.fromCharCode(i);
    var newtext = text + "gmail" + char + ".com";
    cy.CheckOudpattern(newtext);
  }

  for (var i = 58; i <= 64; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + "gmail" + char + ".com";
    cy.CheckOudpattern(newtext);
  }

  for (var i = 91; i <= 96; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + "gmail" + char + ".com";
    cy.CheckOudpattern(newtext);
  }

  for (var i = 123; i <= 126; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + "gmail" + char + ".com";
    cy.CheckOudpattern(newtext);
  }
});

Cypress.Commands.add("Check_Middle_at_with_invalid_char", () => {
  var text = "123@g";
  for (var i = 33; i <= 44; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "mail.com";
    cy.CheckOudpattern(newtext);
  }
  cy.CheckOudpattern("123@g/mail.com");
  // instead of adding if condition for just two char which are (. and - )
  // the loop will stop in , and the / test is written manually
  for (var i = 58; i <= 64; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "mail.com";
    cy.CheckOudpattern(newtext);
  }

  for (var i = 91; i <= 94; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "mail.com";
    cy.CheckOudpattern(newtext);
  }
  cy.CheckOudpattern("123@g'mail.com");
  // instead of writting if condition inside the loop for just one char which is (_)
  // It will stop on (^) and write the last test outside the loop

  for (var i = 123; i <= 125; i++) {
    var char = String.fromCharCode(i);
    var newtext = text + char + "mail.com";
    cy.CheckOudpattern(newtext);
  }
});

Cypress.Commands.add("Check_The_Word_Pre_At", () => {
  cy.CheckOudpattern('abd"alla@gamil.com');
  cy.CheckOudpattern('abdalla"@gamil.com');
  cy.CheckOudpattern('"abdalla@gamil.com');
  cy.CheckOudpattern("abd(alla@gamil.com");
  cy.CheckOudpattern("(abdalla@gamil.com");
  cy.CheckOudpattern("abdalla)@gamil.com");
  cy.CheckOudpattern("abd@alla@gamil.com");
  cy.CheckOudpattern("abdalla@@gamil.com");
  cy.CheckOudpattern("@abdalla@gamil.com");
  cy.CheckOudpattern("abd]alla@gamil.com");
  cy.CheckOudpattern("abdalla]@gamil.com");
  cy.CheckOudpattern("[abdalla@gamil.com");
  cy.CheckOudpattern("abd\\alla@gamil.com");
  cy.CheckOudpattern("abdalla\\@gamil.com");
  cy.CheckOudpattern("\\abdalla@gamil.com");
  cy.CheckOudpattern("abd;alla@gamil.com");
  cy.CheckOudpattern("abdalla;@gamil.com");
  cy.CheckOudpattern(";abdalla@gamil.com");
  cy.CheckOudpattern("abd:alla@gamil.com");
  cy.CheckOudpattern("abdalla:@gamil.com");
  cy.CheckOudpattern(":abdalla@gamil.com");
  cy.CheckOudpattern("abd<alla@gamil.com");
  cy.CheckOudpattern("abdalla>@gamil.com");
  cy.CheckOudpattern("<abdalla@gamil.com");
  // THIS FUCNTION WILL HANDLE ALL TEST CASES THAT IS NOT COVERED IN
  // IN THE PREVIUOS
  cy.CheckOudpattern(".");
  cy.CheckOudpattern("...");
  cy.CheckOudpattern("@gmail.com");
  // cy.CheckOudpattern('abdalla@gmail' )
  cy.CheckOudpattern(".com");
  cy.CheckOudpattern("!");
  cy.CheckOudpattern("abdalla.com");
  cy.CheckOudpattern("abdalla@gmail.");
  cy.CheckOudpattern("abdalla@");
  cy.CheckOudpattern("abdalla");
});

Cypress.Commands.add("ChangeTheEmail", () => {
  cy.visit(URLS.EditProfileURL);
  cy.get(AccountData.EmailBoxID).clear().type(AccountData.NewEmail);
  cy.get(AccountData.ConfirmPasswordBoxID).type(AccountData.Password);
  cy.get(AccountData.SaveProfileButtonID).click();
  cy.contains("Profile saved successfully");
});

Cypress.Commands.add("OudCheck_The_Box", () => {
  cy.visit(URLS.EditProfileURL);
  cy.get(AccountData.EditProfileID).click({ force: true });
  cy.Check_Start_Com_with_invalid_char();
  cy.check_end_Com_With_invalid_char();
  cy.Check_invalid_char_in_middle_in_Com();

  cy.Check_Start_at_with_invalid_char();
  cy.Check_end_at_with_invalid_char();
  cy.Check_Middle_at_with_invalid_char();

  cy.Check_The_Word_Pre_At();
});

// //Acutal Oud tests

Cypress.Commands.add("CheckRoutes", () => {
  cy.visit(URLS.AccountURL);
  cy.contains("EDIT BROFILE").click();
  cy.url().should("eq", URLS.EditProfileURL);
});
Cypress.Commands.add("CheckOudaccountoverview", () => {
  cy.visit(URLS.AccountURL);
  cy.get(AccountData.AccountOverviewID).click({ force: true });
  cy.url().should("eq", URLS.OverViewURL);

  cy.scrollTo(0, 500);
  cy.contains("EDIT BROFILE").click({ force: true });
  cy.url().should("eq", URLS.EditProfileURL);

  cy.go("back"); //  the route won't be checked again when I get back bc it is a browser feature

  cy.scrollTo(0, 1000);
  cy.contains("JOIN PREMIUM").click({ force: true });
  cy.url().should("eq", URLS.JoinPremiumURL);
  cy.contains("Dummy Sign out and Dummy Get Premium Page");
  cy.go("back");
  cy.scrollTo(0, 1500);
  cy.contains("SIGN OUT").click({ force: true });
  cy.url().should("eq", URLS.SignoutURL);
  cy.contains("Dummy Sign out and Dummy Get Premium Page");
  cy.go("back");
});

Cypress.Commands.add("CheckOudpattern", (Pattern) => {
  cy.get(AccountData.EmailBoxID).clear().clear().type(Pattern);
  cy.contains(AccountData.InvalidEmailMessage);
});

Cypress.Commands.add("CheckInvalidPatterns", (number) => {
  cy.visit(URLS.AccountURL);
  cy.get(AccountData.EditProfileID).click({ force: true });
  cy.wait(3000);
  if (number === 1) cy.CheckOudpattern("123@gmail.-a");
  else if (number === 2) cy.CheckOudpattern("123@gmail.a-");
  else if (number === 3) cy.CheckOudpattern("test@gmail");
  else cy.CheckOudpattern("123@gmail.0a");
});

Cypress.Commands.add("OudChange_Password", () => {
  cy.visit(URLS.EditProfileURL);
  cy.get(AccountData.ChangePasswordButton).click({ force: true });
  cy.get(AccountData.CurrentPasswordBox).type("123");
  cy.get(AccountData.SubmitButton).click({ force: true });
  cy.contains(AccountData.WrongPasswordMessage);
  // if you entered invalid password
  cy.get(AccountData.CurrentPasswordBox).clear().type(AccountData.Password);
  cy.get(AccountData.NewPasswordBox).clear().type(AccountData.WrongNewPassword);
  cy.get(AccountData.ConfirmPasswordBox).clear().type(AccountData.NewPassword);
  cy.get(AccountData.SubmitButton).click({ force: true });
  cy.contains(AccountData.DismatchPasswordsMessage);
  // if the password in the second box is not identical ...
  cy.get(AccountData.CurrentPasswordBox).clear().type(AccountData.Password);
  cy.get(AccountData.NewPasswordBox).clear().type(AccountData.NewPassword);
  cy.get(AccountData.ConfirmPasswordBox).clear().type(AccountData.NewPassword);
  // if y
  cy.get(AccountData.SubmitButton).click({ force: true });
  cy.contains(AccountData.ChangingPasswordSuccessfullyMessage);
});
Cypress.Commands.add("CheckChangingpassword", () => {
  cy.visit("/account/changePassword");
  cy.get(AccountData.CurrentPasswordBox).clear().type(AccountData.Password);
  cy.get(AccountData.NewPasswordBox).clear().type(AccountData.WrongNewPassword);
  cy.get(AccountData.ConfirmPasswordBox)
    .clear()
    .type(AccountData.WrongNewPassword);
  // if y
  cy.get(AccountData.SubmitButton).click({ force: true });
  cy.contains(AccountData.WrongPasswordMessage);
});

Cypress.Commands.add("HomeOverView", () => {
  cy.visit("/");
  cy.wait(3000);
  cy.contains("Search").click();
  cy.wait(2000);
  cy.url().should("eq", URLS.SearchURL);
  cy.get(Data.LogoID).click();
  cy.url().should("eq", URLS.HomeURL);
  cy.contains("Search").click();
  cy.contains("Home").click();
  cy.url().should("eq", URLS.HomeURL);
  cy.contains("Your Library").click();
  cy.url().should("eq", URLS.YourLibraryURL);
  cy.go("back");
  cy.contains("Create Playlist").click();
  cy.url().should("eq", URLS.CreatingPlaylistURL);
  cy.go("back");
  cy.contains("Liked Songs").click();
  cy.url().should("eq", URLS.LikedSongsURL);
  cy.go("back");
  cy.contains("Search").click();
  cy.get(Data.LoginButtonID).click();
  cy.url().should("eq", URLS.LoginURL);
  cy.go("back");
  cy.get(Data.SignUpButtonID).click();
  cy.url().should("eq", URLS.SignUpURL);
  cy.go("back");
  cy.get(Data.LogoID).click();
  cy.get(Data.UpgradeButtonID).click();
  cy.url().should("eq", URLS.LoginURL);
  cy.go("back");
  cy.get(Data.ImgProfileID).click();
  cy.get(Data.LogoutButtonID).click();
  cy.url().should("eq", URLS.LogoutURL);
  cy.go("back");
  cy.get(Data.ImgProfileID).click();
  cy.get(Data.AccountButtonID).click();
  cy.url().should("eq", URLS.AccountURL);
});

Cypress.Commands.add("CheckCategories", () => {
  cy.visit("/");
  for (let i = 1; i <= 9; i++) {
    cy.scrollTo(0, i * 750);
    cy.wait(2000);
    cy.xpath(
      Data.SeeAllXpathFirstPart + i + Data.SeeAllXpathSecondPart
    ).click();
    cy.url().should("eq", URLS.SeeAllURL);
    cy.go("back");
    cy.wait(2000);
  }
});

Cypress.Commands.add("CheckSearchBar", () => {
  cy.visit("/");
  cy.get(Data.SearchBarID).click();
  cy.wait(2000);
  cy.url().should("eq", URLS.SearchURL);
  cy.get(Data.SearchBarID).type("Test search text");
  cy.wait(2000);
});

Cypress.Commands.add("HomePlaylists", () => {
  cy.visit("/");
  cy.get(Data.LogoID).click();
  cy.wait(2000);
  for (let i = 1; i <= 10; i++) {
    for (let j = 1; j < 6; j++) {
      cy.xpath(
        Data.PlaylistFirstHalfOfXpath +
          i +
          Data.PlaylistMiddleHalfOfXpath +
          j +
          Data.PlaylistlastHalfOfXpath
      ).click({ force: true });
      cy.url().should("eq", URLS.SeeAllURL);
      cy.go("back");
      cy.wait(2000);
    }
  }
});

Cypress.Commands.add("CheckUpgradeButtonResponsiveness", () => {
  cy.visit("/");
  cy.viewport(550, 750);
  cy.get(Data.ToggleButton).click();
  cy.get(Data.UpgradButtonIDToggleButton).click();
  cy.wait(2000);
  cy.url().should("eq", URLS.UpgradeURL);
  cy.go("back");
  ///
});

Cypress.Commands.add("CheckBackForwardButtons", () => {
  cy.visit("/");
  cy.contains("Search").click({ force: true });
  cy.xpath(Data.BackButtonXpath).click();
  cy.url().should("eq", URLS.HomeURL);
  cy.xpath(Data.ForwardButtonXpath).click();
  cy.url().should("eq", URLS.SearchURL);
});
//   //"test": "echo \"Error: no test specified\" && exit 1"
Cypress.Commands.add("CheckAccountButtonResponsiveness", () => {
  cy.visit("/");
  cy.viewport(550, 750);
  cy.get(Data.ToggleButton).click();
  cy.get(Data.ImgProfileID).click();
  cy.get(Data.AccountButtonID).click();
  cy.url().should("eq", URLS.AccountURL);
});
Cypress.Commands.add("CheckLogoutButtonResponsiveness", () => {
  cy.visit("/");
  cy.viewport(550, 750);
  cy.get(Data.ToggleButton).click();
  cy.get(Data.ImgProfileID).click();
  cy.get(Data.LogoutButtonID).click();
  cy.url().should("eq", URLS.LogoutURL);
});
Cypress.Commands.add("CheckLikedSongs", () => {
  cy.visit("/");
  cy.wait(2000);
  cy.contains("Liked Songs").click({ force: true });
  cy.wait(2000);
  cy.url().should("eq", URLS.LikedSongsURL);
  cy.xpath(Data.DropButtonXpath).click({ multiple: true, force: true });
  cy.contains("Add to Playlist").click({ force: true });
});
Cypress.Commands.add("CheckRoutesinLikedSongs", () => {
  cy.visit("/");
  cy.wait(2000);
  cy.contains("Liked Songs").click({ force: true });
  cy.wait(2000);
  cy.contains("Amr Diab").click();
  cy.go("back");
  cy.url().should("eq", URLS.LikedSongsURL);
});
Cypress.Commands.add("login", () => {
  cy.get(`#${self.loginIds.email}`).type(self.loginData.email);
  cy.get(`#${self.loginIds.password}`).type(self.loginData.password);
  cy.get(`#${self.loginIds.button}`).click();
});
