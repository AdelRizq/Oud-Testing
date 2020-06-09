/// <reference types="cypress"/>

describe("", () => {

    it ('library playlist',()=>{
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


        //Playlists
        cy.get('span').contains('Your Library').click().then(()=>{
              cy.url().should('contain','collection').and('contain','playlists');
              cy.get('h1').contains(`${self.libraryData.emptyPlaylistText}`).should('be.visible');
              cy.get('button').contains(`${self.libraryData.createPlaylist}`).click().then(()=>{
                    cy.get('input[placeholder="New Playlist"]').should('be.visible').type(`${self.libraryData.playlistName}`);
                    cy.get('button').contains(`${self.libraryData.createPlaylistButton}`).click();
              });
          });
          cy.get('span').contains('Your Library').click().then(()=>{
              cy.wait(1000);
            cy.get('nav').get(`.${self.libraryData.container}`).within(()=>{
                cy.get('span').contains(`${self.libraryData.playlistName}`).should('exist').click({force : true}).then(()=>{
                    cy.wait(100);
                });
                
            });
            cy.url().should('contain','playlist');
            cy.get('nav[aria-label="Main"]').within(()=>{
                cy.get('span').contains(`${self.libraryData.playlistName}`).rightclick().then(()=>{
                })     
            });
            cy.get('nav[role="menu"]').within(()=>{
                cy.get('div').get(`.${self.libraryData.deletePlaylistButton}`).contains('Delete').click().then(()=>{
                }); 
            });

                cy.get('button').contains('DELETE').click().then(()=>{
                    cy.wait(1000);
                    cy.get('h1').contains(`${self.libraryData.emptyPlaylistText}`).should('be.visible');
                });
    
            
          });
        })
    });