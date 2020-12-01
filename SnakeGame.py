import pygame, sys,random, time
err = pygame.init()
print("err : ",err)
# init() -> (numpass, numfail)
# initialize all imported pygame modules

if err[1] > 0:
    print(err[1])
    sys.exit(-1)
else:
    print("success")

#play surface :-
playSurface = pygame.display.set_mode((720,460))
# set_mode(size=(0, 0), flags=0, depth=0, display=0)
# -> Surface Initialize a Window or Screen for display!
pygame.display.set_caption('Snakes')
# set_caption(title, icontitle=None)
# -> None Set the current window caption

#time.sleep(5)

# colors :
# Color(name) -> Color Color(r, g, b, a)
# -> Color Color(rgbvalue)
# -> Color pygame object for color representations
red = pygame.Color(255, 0, 0) # gameover
green = pygame.Color(0, 255, 0) #snake
black = pygame.Color(0, 0, 0) #score
white = pygame.Color(255, 255, 255) #background
brown = pygame.Color(165, 42, 42) #food

# Two Types of the Games and other Projects :
# FPS : First Person Shooters
# TPS : Third Person Shooters

# FPS controller
fpsController = pygame.time.Clock()
# Clock() -> Clock create an object to help track time

# Important varibles
snakePos = [100, 50]
snakeBody = [[100, 50], [90, 50], [80, 50]]

foodPos = [random.randrange(1, 72) * 10, random.randrange(1, 46) * 10]
foodSpawn = True

direction = 'RIGHT'
changeto = direction

score = 0


def gameOver():
    myFont = pygame.font.SysFont('monaco', 72)
    GOsurf = myFont.render('Game over!', True, red)
    # render(text, antialias, color, background=None)
    # -> Surface draw text on a new Surface
    GOrect = GOsurf.get_rect()
    GOrect.midtop = (360, 15)
    playSurface.blit(GOsurf, GOrect)
    #showScore(0)
    pygame.display.flip()
    # flip() -> None Update the full display Surface to the screen
    time.sleep(1)  # sleep(seconds)
                # Delay execution for a given number of seconds.
                # The argument may be a floating point number for subsecond precision.
    pygame.quit()  # pygame exit
    sys.exit()  # console exit

def showScore(choice=1):
    sFont = pygame.font.SysFont('monaco', 24)
    Ssurf = sFont.render('Score : {0}'.format(score) , True, black)
    Srect = Ssurf.get_rect()
    if choice == 1:
        Srect.midtop = (80, 10)
    else:
        Srect.midtop = (360, 120)
    playSurface.blit(Ssurf,Srect)


# Main Logic of the game
while True:
    for event in pygame.event.get():
        # get(eventtype=None)
        # -> Eventlist get(eventtype=None, pump=True)
        # -> Eventlist get events from the queue!
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_RIGHT or event.key == ord('d'):
                changeto = 'RIGHT'
            if event.key == pygame.K_LEFT or event.key == ord('a'):
                changeto = 'LEFT'
            if event.key == pygame.K_UP or event.key == ord('w'):
                changeto = 'UP'
            if event.key == pygame.K_DOWN or event.key == ord('s'):
                changeto = 'DOWN'
            if event.key == pygame.K_ESCAPE:
                pygame.event.post(pygame.event.Event(pygame.QUIT))

    # validation of direction
    if changeto == 'RIGHT' and not direction == 'LEFT':
        direction = 'RIGHT'
    if changeto == 'LEFT' and not direction == 'RIGHT':
        direction = 'LEFT'
    if changeto == 'UP' and not direction == 'DOWN':
        direction = 'UP'
    if changeto == 'DOWN' and not direction == 'UP':
        direction = 'DOWN'

    # Update snake position [x,y]
    if direction == 'RIGHT':
        snakePos[0] += 10
    if direction == 'LEFT':
        snakePos[0] -= 10
    if direction == 'UP':
        snakePos[1] -= 10
    if direction == 'DOWN':
        snakePos[1] += 10

    # Snake body mechanism
    snakeBody.insert(0, list(snakePos))
    if snakePos[0] == foodPos[0] and snakePos[1] == foodPos[1]:
        score += 1
        foodSpawn = False
    else:
        snakeBody.pop()

    # Food Spawn
    if foodSpawn == False:
        foodPos = [random.randrange(1, 72) * 10, random.randrange(1, 46) * 10]
    foodSpawn = True

    # Background
    playSurface.fill(white)

    # Draw Snake
    for pos in snakeBody:
        pygame.draw.rect(playSurface, green, pygame.Rect(pos[0], pos[1], 10, 10))
    # rect(Surface, color, Rect, width=0)
    # -> Rect draw a rectangle shape
    pygame.draw.rect(playSurface, brown, pygame.Rect(foodPos[0], foodPos[1], 10, 10))

    # Bound
    if snakePos[0] > 710 or snakePos[0] < 0:
        gameOver()
    if snakePos[1] > 450 or snakePos[1] < 0:
        gameOver()

    # Self hit
    for block in snakeBody[1:]:
        if snakePos[0] == block[0] and snakePos[1] == block[1]:
            gameOver()

    # common stuff
    showScore()
    pygame.display.flip()
    # flip() -> None Update the full display Surface to the screen
    fpsController.tick(25)