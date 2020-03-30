/// <reference types="cypress"/>

describe("Player Test", () => {
    before('Login', () => {
        Cypress.on('uncaught:exception', (err, runnable) => {
            // returning false here prevents Cypress from
            // failing the test
            return false
        })
        cy.Login();
        // Open a library 'lmao'
        cy.get('span').contains('lmao').click();
    });
    beforeEach(() => {
        Cypress.Cookies.preserveOnce();
    });
    it(`PLay-Pause Button`, () => {
        // Play a song in the library 'Bad Liar'
        cy.get('div').contains('Bad Liar').click().then(() => {
            cy.get(`[data-testid="${Cypress.env("dataTestIds").nowPlaying}"]`).should('contain', 'Bad Liar');
            // Play assertions
            cy.get(`button[data-testid="${Cypress.env("dataTestIds").pause}"]`).should('be.visible');
            cy.wait(500);

            //* Apply regular expression someHow
            cy.get(`div[data-testid="${Cypress.env("dataTestIds").progressTime}"]`).invoke('text')
                .should('be.greaterThan', 5);
        });
        // Pause 
        cy.get(`button[data-testid="${Cypress.env("dataTestIds").pause}"]:visible`)
            .click().then(() => {
                // Pause assertions
                cy.get(`button[data-testid="${Cypress.env("dataTestIds").play}"]`).should('be.visible');
                cy.wait(500);

                //* Apply regular expression someHow
                cy.get(`div[data-testid="${Cypress.env("dataTestIds").progressTime}"]`).invoke('text')
                    .should('be.lessThan', 10);
            });
    });

    it('Like Button', () => {
        //open the liked Songs tap
        cy.get(`button[data-testid="${Cypress.env("dataTestIds").likedSongs}"]`).click().then(() => {
            //click like
            cy.get(`button[data-testid="${Cypress.env("dataTestIds").like}"]`).click().then(like, () => {
                cy.get(`div[data-testid="${Cypress.env("classes").likedSongs}"]`).should('contain.text', 'Bad Liar');
                like.click();
                cy.get(`div[data-testid="${Cypress.env("classes").likedSongs}"]`).should('not.contain.text', 'Bad Liar');
            });
        });

    });
    it('Volume button/Bar', () => {
        // Mute
        cy.get(`button[data-testid="${Cypress.env("dataTestIds").volume}"]`).click().then(volumeButton, () => {
            cy.get(`.now-playing-bar__right`).get(`.progress-bar__slider`).then(volume, () => {
                let percentage = volume.getAttribute('style');
                expect(percentage).to.eq('left: translateX(-100%);');
            });
            // unMute
            volumeButton.click();
            cy.get(`.now-playing-bar__right`).get(`.progress-bar__slider`).then(volume, () => {
                let percentage = volume.getAttribute('style');
                expect(percentage).to.eq('left: translateX(0%);');
            });
        });

        // volume Bar 

        cy.get(`.now-playing-bar__right`).get(`.progress-bar__slider`).then(volume, () => {
            //100%
            cy.get(`button[data-testid="${Cypress.env("dataTestIds").volumeBar}"]`).click('right');
            let percentage = volume.getAttribute('style');
            expect(percentage).to.eq('left: translateX(-100%);');

            //0%
            cy.get(`button[data-testid="${Cypress.env("dataTestIds").volumeBar}"]`).click('left');
            let percentage = volume.getAttribute('style');
            expect(percentage).to.eq('left: translateX(0%);');

            //50%
            cy.get(`button[data-testid="${Cypress.env("dataTestIds").volumeBar}"]`).click('center');
            let percentage = volume.getAttribute('style');
            expect(percentage).to.eq('left: translateX(-50%);');
        });

    });

    it('Repete', () => {

    });

    it('Shuffle', () => {


    });

    it('prev/next', () => {


    });



});