/// <reference types="Cypress"/>

describe('Progress/Volume Bars', () => {
    before('vistit Oud', () => {
        cy.fixture('Player/playerIDs.json').then((data) => {
            self.playerIds = data;
        })
        cy.visit('/').then(() => {
            cy.wait(1);

        });
    });



    it('progress Bar', () => {
        cy.wait(1000);
        cy.get(`[data-testid='${self.playerIds.progressBar}']`).click().then(() => {
            cy.get(`[data-testid='${self.playerIds.currentTime}']`).then((cTime) => {
                let pattern = /\d{1}/g; //regular expression
                let timeText = cTime.text();
                let Time = pattern.exec(timeText);
                pattern = /\d{2}/g;
                let Time2 = pattern.exec(timeText);
                let currentTime = Time * 60 + Time2;
                cy.get(`[data-testid='${self.playerIds.totalTime}']`).then((dTime) => {
                    pattern = /\d{1}/g; //regular expression
                    timeText = dTime.text();
                    Time = pattern.exec(timeText);
                    pattern = /\d{2}/g;
                    Time2 = pattern.exec(timeText);
                    let durationTime = Time * 60 + Time2;
                    expect(parseInt(currentTime)).to.be.equal(parseInt(durationTime / 2));
                })
            });
        })
    })


    it('volumeButton', () => {
        cy.wait(1000);
        cy.get(`[data-testid='${self.playerIds.volume}']`).click().then(() => {
            //it has no unique id the volume bar
            cy.xpath('//*[@id="volume-width"]/div/div').should('have.attr', `style`, 'width: 0%;');
        })

        cy.get(`[data-testid='${self.playerIds.volume}']`).click().then(() => {
            //it has no unique id the volume bar
            cy.xpath('//*[@id="volume-width"]/div/div').should('not.have.attr', `style`, 'width: 0%;');
        });
    });

    it('volume Bar', () => {
        cy.get(`[data-testid='${self.playerIds.volumeBar}']`).click().then(() => {
            cy.xpath('//*[@id="volume-width"]/div/div').should('have.attr', `style`, 'width: 50%;');
        });

        cy.get(`[data-testid='${self.playerIds.volumeBar}']`).click('right').then(() => {
            cy.xpath('//*[@id="volume-width"]/div/div').should('have.attr', `style`, 'width: 100%;');
        });

    })

});