#GROUP C === CAR PARKING CODE PROJECT = INTRO & CHOICE 4 - ALEX =======================================================================================================================================================

import random
cap = int(input("Enter the capacity of the Car Parking area (number of slots): "))
filled = 0
carlist = ["Empty"] * cap
taken = []
capint = (cap - 1)


#filled if for the present cars occupying the car parking area
#carlist is to display the number of cars present and their plate numbers aswell as empty cars whenever needed
#taken is a seperate list for taken car slots while randomly generating a slot
#capint is -1 because 0 is also counted as an index in a list this is for when randomly generating a carslot


#looping so the person using the code can run it as many times as they want while keeping past results until the program is exited
while(True):
    print("\n1: Park a new car")
    print("2: Make a car drive out")
    print("3: Display number of filled and empty parking slots")
    print("4: Exit")
    print("[Choose 1-4]")

    #provide the user with options of which part of the code will be ran
    choice = input("\nEnter a choice: ")
    
    #if 4 is picked code breaks
    if(choice == "4"):
        print("\n--EXITING--")
        break

    
#CHOICE 1- RAYMOND =====================================================================================================================================================================================

    
    elif(choice == "1"):
        #when 1 is picked it will first check if parking is full, if its full notify the user
        if(filled == cap):
            print("\n---------------All the slots are full---------------")

        else:
            #if parking is not full then request plate number
            plate = str(input("Enter your plate number: "))
            #check to see if the car is already parked here
            if plate in carlist:
                print("\n---This car is already parked.---")
                #increase the number of cars present
                #find the index of an empty car slot in the list then add the plate number in the vacant spot
            else:
                #different while looop for randomly selecting a car slot
                while(True):
                    num = random.randint(0,capint)
                    if num in taken:
                        continue
                    filled = filled + 1
                    carlist.pop(num)
                    carlist.insert(num, plate)
                    #add the car slot to a seprate list so it doesnt override
                    taken.append(num)
                    print("\n--------------------The Car has filled a slot.-----------------------------")
                    break
                    
                
                
 # CHOICE 2- SEBASTIAN =================================================================================================================================================================================-


 
    elif(choice == "2"):
        #when 2 is picked first check if theres any cars parked, if there are no cars it will notify the user
        if(filled == 0):
            print("\n---------------There are no cars to drive out.---------------")
            #if there is cars present then request plate number
        else:
            plate = str(input("Enter your plate number: "))
            #decrease the number of cars present
            #find the index of the plate number in the list then remove the plate number and add a vacant spot
            if plate in carlist:
                filled = filled - 1
                index = carlist.index(plate)
                carlist.pop(index)
                #also remove the slot from the random section
                taken.remove(index)
                carlist.insert(index, "Empty")
                print("\n---------------------The Car has left.----------------")
                #if the plate number is not valid(meaning theres no cars parked with that plate number)it will tell you its invalid.
            else:
                print("\n--invalid plate number.--")

                
            
# CHOICE 3 & ELSE - KISE ==============================================================================================================================================================================


    #print results
    elif(choice == "3"):
        print("\n--------------------Status of Car Parking area----------------------------")
        print("Total Number of slots: " + str(cap))
        print("Number of filled slots: " + str(filled))
        print("Number of empty slots: " + str((cap - filled)))
        print(carlist)

    else:
        #if the user inputs a number or letter that isnt 1 2 3 or 4
        print("\nPlease enter a valid option")
