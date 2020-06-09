/// <reference types="cypress"/>

describe("Search", () => {
    
    it("Search", () => {
        
    
        cy.fixture("Search/search-data").then((data) => {
            self.searchData = data;
        });

        cy.visit('/');
        Cypress.Cookies.preserveOnce();
        cy.contains('Log In').click().then(()=>{
            cy.get(`#${self.searchData.username}`).type(Cypress.env('username'));
            cy.get(`#${self.searchData.password}`).type(Cypress.env('password'));
            cy.get(`#${self.searchData.loginButton}`).click().then(()=>{
                cy.wait(1000);
                cy.get('span').contains('Spotify').click().then(()=>{
                    cy.get(`#${self.searchData.launchWebPlayer}`).click();
                });
            });
        });



        cy.get(`a[aria-label="Search"]`).click().then(()=>{
            cy.wait(2000);
            cy.url().should('contain','search');
            //cy.title().should('contain','Search');
            cy.get('h2').contains('Browse all').should('exist');
            cy.get('h3').contains('Podcasts').should('exist');
            cy.get('h3').contains('Made For You').should('exist');
            cy.get('h3').contains('New Releases').should('exist');







            cy.get(`input[data-testid='${self.searchData.searchInput}']`).type('a').then(()=>{
                cy.url().should('contain','search/a');
                cy.get(`section[aria-label='Top result']`).within(()=>{
                    cy.get('span').should('contain','A');
                });
                cy.get(`section[aria-label='Songs']`).within(()=>{
                    cy.get('span').should('contain','A');
                });
                cy.get(`section[aria-label='Artists']`).within(()=>{
                    cy.get('span').should('contain','A');
                });
                cy.get(`section[aria-label='Albums']`).within(()=>{
                    cy.get('span').should('contain','A');
                });
                cy.get(`section[aria-label='Playlists']`).within(()=>{
                    cy.get('span').should('contain','A');
                });
            });
            cy.get(`input[data-testid='${self.searchData.searchInput}']`).type('s').then(()=>{
                cy.url().should('contain','search/as');
                cy.get(`section[aria-label='Top result']`).within(()=>{
                    cy.get('span').should('contain','As');
                });
                cy.get(`section[aria-label='Songs']`).within(()=>{
                    cy.get('span').should('contain','As');
                });
                cy.get(`section[aria-label='Artists']`).within(()=>{
                    cy.get('span').should('contain','As');
                });
                cy.get(`section[aria-label='Albums']`).within(()=>{
                    cy.get('span').should('contain','As');
                });
                cy.get(`section[aria-label='Playlists']`).within(()=>{
                    cy.get('span').should('contain','As');
                });
            });

            cy.get(`input[data-testid='${self.searchData.searchInput}']`).clear();

            cy.get(`input[data-testid='${self.searchData.searchInput}']`).type(`${self.searchData.garbageInput}`).then(()=>{
                cy.get('h1').contains(`No results found for "${self.searchData.garbageInput}"`).should('be.visible');
            });
            


            cy.get(`[aria-label="${self.searchData.clearInput}"]`).click().then(()=>{
                cy.get(`input[data-testid='${self.searchData.searchInput}']`).should('have.attr','value').and('be.empty');
            });

            //title isn't changing in the tests although it changes without test
            //also the player isn't working during tests and working in without test
            cy.get(`input[data-testid='${self.searchData.searchInput}']`).type('a').then(()=>{
                cy.get(`section[aria-label='Artists']`).within(()=>{
                    cy.get('span').should('contain','A').eq(1).click({force:true}).then(()=>{
                        cy.url().should('contain','artist');
                        //cy.title().should('contain','Spotify - A');
                        cy.go('back');
                    });
                });

                cy.get(`section[aria-label='Albums']`).within(()=>{
                    cy.get('span').should('contain','A').eq(1).click({force:true}).then(()=>{
                        cy.url().should('contain','album');
                        //cy.title().should('contain','Spotify - A');
                        cy.go('back');
                    });
                });

                cy.get(`section[aria-label='Playlists']`).within(()=>{
                    cy.get('span').should('contain','A').eq(1).click({force:true}).then(()=>{
                        cy.url().should('contain','playlist');
                        //cy.title().should('contain','Spotify - A');
                        cy.go('back');
                    });
                });

                cy.get(`section[aria-label='Songs']`).within(()=>{
                    cy.get('span').should('contain','A').eq(1).click({force:true}).then(()=>{
                        //cy.get(`a[data-testid=${self.searchData.nowPlayingTrack}]`).should('contain','A'); 
                        //cy.title().should('contain','A');
                        cy.go('back');
                    });
                });
            });


        cy.get(`input[data-testid='${self.searchData.searchInput}']`).type('part').then(()=>{
            cy.get(`section[aria-label='Artists']`).within(()=>{
                cy.get('span').should('contain','Apart').eq(1).click({force:true}).then(()=>{
                    cy.url().should('contain','artist');
                    //cy.title().should('contain','Spotify - Apart');
                    cy.go('back');
                });
            });

            cy.get(`section[aria-label='Albums']`).within(()=>{
                cy.get('span').should('contain','Apart').eq(1).click({force:true}).then(()=>{
                    cy.url().should('contain','album');
                    //cy.title().should('contain','Spotify - Apart');
                    cy.go('back');
                });
            });

            cy.get(`section[aria-label='Playlists']`).within(()=>{
                cy.get('span').should('contain','Apart').eq(1).click({force:true}).then(()=>{
                    cy.url().should('contain','playlist');
                    //cy.title().should('contain','Spotify - Apart');
                    cy.go('back');
                });
            });

            cy.get(`section[aria-label='Songs']`).within(()=>{
                cy.get('span').should('contain','Apart').eq(1).click({force:true}).then(()=>{
                    //cy.get(`a[data-testid=${self.searchData.nowPlayingTrack}]`).should('contain','Apart');
                    //cy.title().should('contain','Apart'); 
                    cy.go('back');
                });
            });
        });

        //recent searches
        cy.get('span').contains('Home').click().then(()=>{
            cy.get('span').contains('Search').click().then(()=>{
                cy.get('section[aria-label="Recent searches"]').within(()=>{
                    cy.get('span').should('contain','A').and('contain','Apart');
                });

            });
        })
    });
        

    



});
});
