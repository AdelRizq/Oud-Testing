/// <reference types="cypress"/>

describe("", () => {

    it ('library search',()=>{
        //logging in
        cy.fixture("Library/library-data").then((data) => {
            self.libraryData = data;
          });
          cy.visit('/');
          Cypress.Cookies.preserveOnce();
          cy.contains('Log In').click().then(()=>{
            cy.get(`#${self.libraryData.username}`).type(Cypress.env('username'));
            cy.get(`#${self.libraryData.password}`).type(Cypress.env('password'));
            cy.get(`#${self.libraryData.loginButton}`).click().then(()=>{
                cy.wait(1000);
                cy.get('span').contains('Spotify').click().then(()=>{
                    cy.get(`#${self.libraryData.launchWebPlayer}`).click();
                });
            });
          });
          Cypress.config('defaultCommandTimeout',10000);

          
        //Artists
        cy.get('span').contains('Your Library').click().then(()=>{
            cy.wait(1000);
            cy.get('span').contains('Artists').click().then(()=>{
                cy.url().should('contain','artists');
                cy.get('h1').contains(`${self.libraryData.emptyArtistsText}`).should('be.visible');
                cy.get('a').contains(`${self.libraryData.findArtistButton}`).click().then(()=>{
                    cy.url().should('contain','search');
                    cy.get(`input[data-testid='${self.libraryData.searchInput}']`).type('sia').then(()=>{
                        cy.wait(1000);
                        cy.get('section[aria-label="Artists"]').within(()=>{
                            cy.get('span').contains('Sia').first().click({force:true}).then(()=>{
                                
                            })
                        })
                        cy.wait(5000);
                        cy.get(`button${self.libraryData.followButton}`).click({force:true}).then(()=>{
                            cy.get('span').contains('Your Library').click().then(()=>{
                                cy.get('span').contains('Artists').click().then(()=>{
                                    cy.get('section').get(`.${self.libraryData.container}`).within(()=>{
                                        cy.wait(1000);
                                        cy.get('span').contains('Sia').should('exist').click({force:true}).then(()=>{
                                            
                                        });
                                    });
                                    cy.get('h1').contains('Sia').should('be.visible');
                                    cy.url().should('contain','artist');
                                    cy.get('div.c35a53c76b57e4920749760846dec11d-scss').within(()=>{
                                        cy.get(`button[type="button"]`).first().click({force:true}).then(()=>{
                                        })
                                    });
                                    
                                    cy.get('span').contains('Your Library').click().then(()=>{
                                        cy.get('span').contains('Artists').click().then(()=>{
                                            cy.get('h1').contains(`${self.libraryData.emptyArtistsText}`).should('be.visible');
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            }); 
        });

    });
});