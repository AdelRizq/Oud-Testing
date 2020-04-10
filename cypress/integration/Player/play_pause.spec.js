/// <reference types="Cypress"/>

describe('Play/Pause', () => {
    before('vistit Oud', () => {
        cy.fixture('Player/playerIDs.json').then((data) => {
            self.playerIds = data;
        })
        cy.visit('/').then(() => {
            cy.wait(1);

        });
    });


    it('play button', () => {
        cy.wait(1000);
        //i used xpath because when i try to get this button with the test id it gives me 61 element not just 1
        cy.xpath('//*[@id="root"]/div/div[4]/div/div[1]/div/div[2]/div/button[2]').click({ multiple: true, force: true }).then((play) => {
            //play assertion
            cy.get(`[data-testid='${self.playerIds.pause}']`).should('be.visible');
            cy.wait(5000);
            cy.get(`[data-testid='${self.playerIds.currentTime}']`).then((cTime) => {
                let pattern = /\d{2}/g; //regular expression
                let timeText = cTime.text();
                let Time = pattern.exec(timeText);
                expect(parseInt(Time)).to.be.equal(5);
                //waiting 5 second after clicking play button then check if the time in bar is equal to 5
            });
        })
    });




    it('pause button', () => {
        cy.wait(1000);
        cy.get(`[data-testid='${self.playerIds.pause}']`).click({ multiple: true, force: true }).then((play) => {
            cy.get(`[data-testid='current-time']`).then((cTime) => {
                let timeText = cTime.text();

                //pause assertion
                cy.get(`[data-testid='${self.playerIds.play}']`).should('be.visible');
                cy.wait(5000).then(() => {
                    let timeText2 = cTime.text();
                    expect(timeText).to.be.equal(timeText2);
                });

            });
        })
    })




    it(`volumeBar "afterClicking on the play button"`, () => {
        cy.wait(1000);
        cy.get(`[data-testid='${self.playerIds.volumeBar}']`).click('right').then(() => {
            cy.xpath('//*[@id="volume-width"]/div/div').should('have.attr', `style`, 'width: 100%;');
        });

        cy.get(`[data-testid='${self.playerIds.volumeBar}']`).click().then(() => {
            cy.xpath('//*[@id="volume-width"]/div/div').should('have.attr', `style`, 'width: 50%;');
        });


    });




    it(`progress Bar 'after clicking on the play button once then pause'`, () => {
        cy.wait(1000);
        cy.get(`[data-testid='${self.playerIds.progressBar}']`).click('left').then(() => {
            cy.get(`[data-testid='${self.playerIds.currentTime}']`).should('have.text', '0.00');
        });

        cy.get(`[data-testid='${self.playerIds.progressBar}']`).click().then(() => {
            cy.get(`[data-testid='${self.playerIds.currentTime}']`).then((cTime) => {
                let pattern = /\d{1}/g; //regular expression
                let timeText = cTime.text();
                let Time = pattern.exec(timeText);
                pattern = /\d{2}/g;
                let Time2 = pattern.exec(timeText);
                let currentTime = Time * 60 + Time2;
                cy.get(`[data-testid='duration-time']`).then((dTime) => {
                    pattern = /\d{1}/g; //regular expression
                    timeText = cTime.text();
                    Time = pattern.exec(timeText);
                    pattern = /\d{2}/g;
                    Time2 = pattern.exec(timeText);
                    let durationTime = Time * 60 + Time2;
                    expect(Math.abs(currentTime - (durationTime / 2))).lessThan(3);
                })


                //waiting 5 second after clicking play button then check if the time in bar is equal to 5
            });
        });
    })




    it(`progress Bar 'while the play button is clicked'`, () => {
        cy.wait(1000);
        cy.xpath('//*[@id="root"]/div/div[4]/div/div[1]/div/div[2]/div/button[2]').click();

        cy.get(`[data-testid='${self.playerIds.progressBar}']`).click().then(() => {
            cy.wait(1000).then(() => {
                cy.get(`[data-testid='${self.playerIds.currentTime}']`).then((cTime) => {
                    let pattern = /\d{1}/g; //regular expression
                    let timeText = cTime.text();
                    let Time = pattern.exec(timeText);
                    pattern = /\d{2}/g;
                    let Time2 = pattern.exec(timeText);
                    let currentTime = parseInt(Time) * 60 + parseInt(Time2);
                    cy.get(`[data-testid='${self.playerIds.totalTime}']`).then((dTime) => {

                            pattern = /\d{1}/g; //regular expression
                            timeText = dTime.text();
                            Time = pattern.exec(timeText);
                            pattern = /\d{2}/g;
                            Time2 = pattern.exec(timeText);
                            let durationTime = parseInt(Time) * 60 + parseInt(Time2);
                            expect(Math.abs(parseInt(currentTime) - parseInt(durationTime / 2))).lessThan(3);
                        })
                        //waiting 5 second after clicking play button then check if the time in bar is equal to 5
                });

            }); //it responds a little bit slow

        });
        cy.get(`[data-testid='${self.playerIds.progressBar}']`).click('left');
        cy.get(`[data-testid='${self.playerIds.pause}']`).click();
        cy.get(`[data-testid='${self.playerIds.currentTime}']`).should('have.text', '0.02');
        cy.log("it responds a little bit slow so it's 0.02 not 0.00 ");

        cy.get(`[data-testid='${self.playerIds.trackName}']`).then((trackName) => {
            let Name = trackName.text();
            cy.xpath('//*[@id="root"]/div/div[4]/div/div[1]/div/div[2]/div/button[2]').click();
            cy.get(`[data-testid='${self.playerIds.progressBar}']`).click('right');
            cy.wait(5000); // wait untill the song ends and the other one starts
            cy.get(`[data-testid='${self.playerIds.trackName}']`).then((trackName2) => {
                let Name2 = trackName2.text();
                expect(Name).not.eq(Name2);
            })
        });
    })
});