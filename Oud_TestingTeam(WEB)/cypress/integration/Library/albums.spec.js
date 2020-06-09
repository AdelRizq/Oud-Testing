/// <reference types="cypress"/>

describe("Search", () => {

    it ('library albums',()=>{
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


        cy.get('span').contains('Your Library').click().then(()=>{
            cy.wait(1000);
        });

        //albums
        cy.get('span').contains('Albums').click().then(()=>{
            cy.url().should('contain','albums');
            cy.get('h1').contains(`${self.libraryData.emptyAlbumsText}`).should('be.visible');
            cy.get('a').contains(`${self.libraryData.findAlbumButton}`).click().then(()=>{
                cy.url().should('contain','search');
                cy.get(`input[data-testid='${self.libraryData.searchInput}']`).type('Hello').then(()=>{
                    cy.wait(1000);
                    cy.get('section[aria-label="Albums"]').within(()=>{
                        cy.get('span').contains('Hello').first().click({force:true}).then(()=>{
                            
                        })
                    })
                    cy.get(`button[title="${self.libraryData.likeButton}"]`).click({force:true}).then(()=>{
                        cy.get('span').contains('Your Library').click().then(()=>{
                            cy.get('span').contains('Albums').click().then(()=>{
                                cy.wait(1000);
                                cy.get('section').get(`.${self.libraryData.container}`).within(()=>{
                                    cy.get('span').contains('Hello').click({force:true}).then(()=>{
                                        
                                    });
                                });
                                cy.get('h1').contains('Hello').should('be.visible');
                                cy.url().should('contain','album');
                                cy.get(`button[title='${self.libraryData.unlikeButton}']`).click({force:true}).then(()=>{
                                    cy.get('span').contains('Your Library').click().then(()=>{
                                        cy.get('span').contains('Albums').click().then(()=>{
                                            cy.get('h1').contains(`${self.libraryData.emptyAlbumsText}`).should('be.visible');
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        }); 






        //Podcasts
        cy.get('span').contains('Your Library').click().then(()=>{
            cy.wait(1000);
        });
        cy.get('span').contains('Podcasts').click().then(()=>{
            cy.url().should('contain','podcasts');
            cy.get('h1').contains(`${self.libraryData.emptyPodcastText}`).should('be.visible');
            cy.get('a').contains(`${self.libraryData.findPodcastButton}`).click().then(()=>{
                cy.url().should('contain','search');
            });
        });  


    });
});